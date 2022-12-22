package org.slzdevsnp.clients.wattsight;

import org.slzdevsnp.clients.wattsight.model.WattsightAuthenticationToken;
import org.slzdevsnp.restclients.common.model.ClientResponse;
import org.slzdevsnp.restclients.common.model.RestRequest;
import org.slzdevsnp.restclients.common.model.RestResponse;
import org.slzdevsnp.restclients.common.utils.RestUtils;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public final class WattsightClient {
    private WattsightAuthenticationToken token;
    private ObjectMapper objectMapper;

    private String clientId;
    private String clientSecretKey;

    private WattsightClient(
            WattsightAuthenticationToken token,
            ObjectMapper objectMapper,
            String clientId,
            String clientSecretKey) {
        this.token = token;
        this.objectMapper = objectMapper;
        this.clientId = clientId;
        this.clientSecretKey = clientSecretKey;
    }


    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public WattsightAuthenticationToken getToken() {
        return token;
    }

    public static WattsightClient login(String clientId, String clientSecretKey) throws IOException {
        String url = "https://auth.wattsight.com/oauth2/token";

        HttpResponse<String> response = Unirest.post(url)
                .basicAuth(clientId, clientSecretKey)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("grant_type=client_credentials")
                .asString();

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed to Login (http:" + response.getStatus() + ") - " +
                    response.getBody());
        }

        ObjectMapper objectMapper = createObjectMapper();

        WattsightAuthenticationToken token = objectMapper.readValue(response.getBody(), WattsightAuthenticationToken.class);
        token.setCreatedAt(LocalDateTime.now()); //set WattsightAuthenticationToken field createdAt

        //log.debug("login resp body:{}",response.getBody());

        return new WattsightClient(token, objectMapper, clientId, clientSecretKey);
    }

    public static WattsightClient withToken(WattsightAuthenticationToken token) {
        return new WattsightClient(token, createObjectMapper(), null, null);
    }


    /**
     * Refreshes the authentication token being used by this instances
     */
    protected void refreshToken() {
        try {
            WattsightClient newClient = WattsightClient.login(this.clientId, this.clientSecretKey);
            this.token = newClient.getToken();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    protected RestResponse restCall(RestRequest request) {
        if (token.hasExpired()) {
            this.refreshToken();
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token.getAccessToken()); //used for basic auth
        return RestUtils.restCall(
                this.objectMapper,
                request,
                null,
                headers
        );
    }


    protected <T> void parseResponse(
            RestResponse restResponse,
            ClientResponse<?> clientResponse,
            Class<T> clazz) {
        RestUtils.parseResponse(
                this.objectMapper,
                restResponse,
                clientResponse,
                clazz
        );
    }

    public AreasClient areasClient() {
        return new AreasClient(this);
    }

    public CategoriesClient categoriesClient() {
        return new CategoriesClient(this);
    }

    public CommoditiesClient commoditiesClient() {
        return new CommoditiesClient(this);
    }

    public CurveStatesClient curveStatesClient() {
        return new CurveStatesClient(this);
    }

    public CurveTypesClient curveTypesClient() {
        return new CurveTypesClient(this);
    }

    public CurvesClient curvesClient() {
        return new CurvesClient(this);
    }

    public InstancesClient instanceClient() {
        return new InstancesClient(this);
    }

    public TaggedInstancesClient taggedInstancesClient() {
        return new TaggedInstancesClient(this);
    }

    public SeriesClient seriesClient() {
        return new SeriesClient(this);
    }
}
