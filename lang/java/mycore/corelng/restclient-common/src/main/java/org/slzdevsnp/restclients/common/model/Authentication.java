package org.slzdevsnp.restclients.common.model;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authentication {
    private Boolean basicAuth;
    private String username;
    private String password;
}
