package org.slzdevsnp.lomb.model;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoginResultDTO {

    @Test
    public void testVoidLoginDTO(){
        //LoginResultDTO  lr = new LoginResultDTO(); // this wil not work as constructor with args required
        assertTrue(true);
    }

    @Test
    public void testLoginDTO()throws MalformedURLException {
        // we did not implement the constructor in LoginResultDTO. yet it was generated
        LoginResultDTO lr = new LoginResultDTO(
                Instant.now(),
                "abcd",
                Duration.ofHours(1),
                new URL("http://localhost"));

        assertTrue(lr != null);
    }
}
