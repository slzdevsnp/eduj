package org.slzdevsnp.clients.wattsight.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WattsightAuthenticationToken {
    private String accessToken;
    private String tokenType;
    private Integer expiresIn;

    private LocalDateTime createdAt;


    public boolean hasExpired() {
        return hasExpired(LocalDateTime.now());
    }

    public boolean hasExpired(LocalDateTime at) {
        return !createdAt.plusSeconds(expiresIn).minusMinutes(5l).isAfter(at);
    }
}
