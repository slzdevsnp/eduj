package org.slzdevsnp.clients.wattsight.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class WattsightAuthenticationTokenTest {

    @Test
    public void shouldDetectTokenHasExpired() {
        LocalDateTime now = LocalDateTime.of(2020, 1, 1, 0, 0, 0);

        WattsightAuthenticationToken token = WattsightAuthenticationToken.builder()
                .expiresIn(3600)
                .createdAt(now)
                .build();

        assertThat(token.hasExpired(now), is(false));
        assertThat(token.hasExpired(now.plusDays(1)), is(true));
        //at exactly 5 minutes before the hour it should expire
        assertThat(token.hasExpired(now.plusHours(1).minusMinutes(5)), is(true));
        assertThat(token.hasExpired(now.plusHours(1).minusMinutes(5).minusSeconds(1)), is(false));

    }

}