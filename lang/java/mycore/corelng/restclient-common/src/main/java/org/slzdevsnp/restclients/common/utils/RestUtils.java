package org.slzdevsnp.restclients.common.utils;

import org.slzdevsnp.restclients.common.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slzdevsnp.restclients.common.model.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RestUtils {

    public static <T> void parseResponse(
            ObjectMapper objectMapper,
            RestResponse restResponse,
            ClientResponse<?> clientResponse,
            Class<T> clazz) {
        clientResponse.setHttpResponseCode(restResponse.getHttpCode());
        clientResponse.setRaw(restResponse.getResponseBody());
        clientResponse.setHttpError(Boolean.FALSE);

        if (restResponse.getError() == Boolean.TRUE) {
            clientResponse.setHttpError(Boolean.TRUE);
            clientResponse.setHttpErrorMessage(restResponse.getErrorMessage());
            return;
        }

        clientResponse.setHeaders(restResponse.getHeaders());
        clientResponse.setRequestMethod(restResponse.getRequestMethod());
        clientResponse.setRequestPath(restResponse.getRequestPath());
        if (StringUtils.isNotBlank(restResponse.getResponseBody())) {
            try {
                clientResponse.setRawJson(objectMapper.readTree(restResponse.getResponseBody()));
                T t = objectMapper.readValue(restResponse.getResponseBody(), clazz);

                if (clientResponse instanceof ClientListResponse) {
                    ClientListResponse clientListResponse = (ClientListResponse) clientResponse;
                    //assign array of objectMapped entities
                    clientListResponse.setEntity(Arrays.asList((Object[]) t));
                } else {
                    ClientSingleResponse clientSingleResponse = (ClientSingleResponse) clientResponse;
                    clientSingleResponse.setEntity(t);
                }
                clientResponse.setValidJsonResponse(Boolean.TRUE);
            } catch (Exception e) {
                log.warn("Could not parse response - {}", restResponse.getResponseBody());

                log.warn(e.getMessage(), e);
                clientResponse.setValidJsonResponse(Boolean.FALSE);
                clientResponse.setJsonError(e.getMessage());
            }
        }
        log.trace("Response - {}", clientResponse);
    }


    public static RestResponse restCall(
            ObjectMapper objectMapper,
            RestRequest request,
            Authentication authentication,
            Map<String, String> headers) {
        String b = request.getBody();
        try {
            if (request.getBodyEntity() != null) {
                b = objectMapper.writeValueAsString(request.getBodyEntity());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        log.debug("\nRequest>>> \n{}: {}\n{}\n<<<",
                request.getMethod(),
                request.getUrl(),
                b == null ? "<NO-BODY>" : b);

        RestResponse.RestResponseBuilder builder = RestResponse.builder();

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Accept", "application/json");
        requestHeaders.put("Content-Type", "application/json");
        if (headers != null) {
            requestHeaders.putAll(headers);
        }

        HttpResponse<String> response = null;

        try {
            //regexp for purl path encoding
            String reqPath = request.getUrl().replace("+", "%2B");
            reqPath = reqPath.replace("|", "%7C");
            builder.requestPath(reqPath);
            builder.requestMethod(request.getMethod());

            if ("get".equalsIgnoreCase(request.getMethod())) {
                GetRequest getRequest = Unirest.get(reqPath)
                        .headers(requestHeaders)
                        .connectTimeout(10_000);
                if (authentication != null && authentication.getBasicAuth() == Boolean.TRUE) {
                    getRequest.basicAuth(authentication.getUsername(), authentication.getPassword());
                }
                response = getRequest.asString();

            } else if ("put".equalsIgnoreCase(request.getMethod())) {
                RequestBodyEntity requestBodyEntity = Unirest.put(reqPath)
                        .body(b)
                        .headers(requestHeaders)
                        .connectTimeout(10_000);
                if (authentication != null && authentication.getBasicAuth() == Boolean.TRUE) {
                    requestBodyEntity.basicAuth(authentication.getUsername(), authentication.getPassword());
                }
                response = requestBodyEntity.asString();
            } else if ("post".equalsIgnoreCase(request.getMethod())) {
                RequestBodyEntity requestBodyEntity = Unirest.post(reqPath)
                        .body(b)
                        .headers(requestHeaders)
                        .connectTimeout(10_000);
                if (authentication != null && authentication.getBasicAuth() == Boolean.TRUE) {
                    requestBodyEntity.basicAuth(authentication.getUsername(), authentication.getPassword());
                }
                response = requestBodyEntity.asString();
            } else if ("delete".equalsIgnoreCase(request.getMethod())) {
                HttpRequestWithBody delete =
                        Unirest.delete(reqPath)
                                .connectTimeout(10_000)
                                .headers(requestHeaders);
                if (authentication != null && authentication.getBasicAuth() == Boolean.TRUE) {
                    delete.basicAuth(authentication.getUsername(), authentication.getPassword());
                }
                if (StringUtils.isNotBlank(b)) {
                    delete.body(b);
                }
                response = delete.asString();
            }

            if (response != null) {
                log.debug("\nResponse>>> \nCODE:{}\n{}\n<<<",
                        response.getStatus(),
                        response.getBody());

                builder.httpCode(response.getStatus());
                builder.error(!response.isSuccess());
            } else {
                builder.httpCode(700);
            }

            builder.responseBody(response != null ? response.getBody() : null);
            for (Header hdr : response.getHeaders().all()) {
                builder.header(hdr.getName(), Arrays.asList(hdr.getValue()));
                log.debug("response header key:{} value:{}",hdr.getName(), hdr.getValue());
            }

        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            builder.error(true);
            builder.errorMessage(e.getMessage());
        }
        return builder.build();
    }
}
