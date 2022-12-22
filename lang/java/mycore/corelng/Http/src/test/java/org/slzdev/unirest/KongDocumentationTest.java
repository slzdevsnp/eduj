package org.slzdev.unirest;


//usrl http://kong.github.io/unirest-java/

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Slf4j
public class KongDocumentationTest {


    @Test
    public void processBasicPostRequsest() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .field("parameter", "value")
                .field("firstname", "Gary")
                .asJson();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void processRequestWRootParams() throws UnirestException {
        HttpResponse<String> response = Unirest.get("http://httpbin.org/{fruit}")
                .routeParam("fruit", "apple")
                .asString();
        log.info("response:{}",response.getBody());
        assertEquals(404, response.getStatus());
    }

}
