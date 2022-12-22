package org.slzdevsnp.restclients.common.model;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestRequest {
    private String url;
    private String method;
    private String body;

    private Object bodyEntity;

}
