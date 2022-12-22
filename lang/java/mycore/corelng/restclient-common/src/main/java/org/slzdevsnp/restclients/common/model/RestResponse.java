package org.slzdevsnp.restclients.common.model;

import lombok.*;

import java.util.List;
import java.util.Map;


@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse {
    private Integer httpCode;
    private String responseBody;
    @Singular("header")
    private Map<String, List<String>> headers;
    private Boolean error;
    private String errorMessage;

    private String requestPath;
    private String requestMethod;
}
