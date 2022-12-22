package org.slzdevsnp.lomb.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAppConfigBuilderPattern {

    @Test
    public void testAppConfigBuilderPatter(){
        AppConfigBuilderPattern config =
                AppConfigBuilderPattern.builder()
                        .host("api.server.com")
                        .port(443)
                        .useHttps(true)
                        .connectTimeout(15_000L)
                        .readTimeout(5_000L)
                        .username("myusername")
                        .password("secret")
                        .build();
        assertEquals(config.getPort(), 443);
        assertEquals(config.getUsername(), "myusername");
        assertTrue(config != null);
    }
}
