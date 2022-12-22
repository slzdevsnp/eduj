package org.slzdevsnp.restclients.common.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class ClientResponse<T> {
    private Boolean httpError;
    private String httpErrorMessage;

    private String raw;
    private JsonNode rawJson;
    private Integer httpResponseCode;

    private Boolean validJsonResponse;
    private String jsonError;

    private Map<String, List<String>> headers;

    private String requestPath;
    private String requestMethod;
}
