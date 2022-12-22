package org.slzdev.unirest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@Slf4j
public class HttpClientLiveTest {


    @BeforeClass
    public static void setup() {
 /*       // Unirest.setProxy(new HttpHost("localhost", 8080));
        //belowe setters are deprecated in konghq
        Unirest.setTimeouts(20000, 15000);
        Unirest.setDefaultHeader("X-app-name", "baeldung-unirest");
        Unirest.setDefaultHeader("X-request-id", "100004f00ab5");
        Unirest.setConcurrency(20, 5);
        Unirest.setObjectMapper(new ObjectMapper() {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

            }

            public <T> T readValue(String value, Class<T> valueType) {

                try {
                    return mapper.readValue(value, valueType);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });*/
        Unirest.config().connectTimeout(7000)
                .socketTimeout(5000)
                .concurrency(20,5)
                .addDefaultHeader("X-app-name", "baeldung-unirest")
                .addDefaultHeader("X-request-id", "100004f00ab5");

    }

    @AfterClass
    public static void tearDown() {
        //Unirest.clearDefaultHeaders();
        //Unirest.shutdown();
    }

    @Test
    public void shouldReturnStatusOkay() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.get("http://www.mocky.io/v2/5a9ce37b3100004f00ab5154")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .asJson();
        log.info("body:{}",jsonResponse.getBody());
        assertNotNull(jsonResponse.getBody());
        assertEquals(200, jsonResponse.getStatus());
    }

    @Test
    public void giveMyMockySeeReturnStatus() {
        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://run.mocky.io/v3/337a030b-2f77-4f24-ac07-7ec8647cd107")
                .header("accept", "application/json")
                .asJson();
        log.info("body:{}",jsonResponse.getBody());
        assertEquals(200, jsonResponse.getStatus());
    }

    @Test
    public void shouldReturnStatusAccepted() throws UnirestException {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("accept", "application/json");
        headers.put("Authorization", "Bearer 5a9ce37b3100004f00ab5154");

        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("name", "Sam Baeldung");
        fields.put("id", "PSP123");

        HttpResponse<JsonNode> jsonResponse = Unirest.put("http://www.mocky.io/v2/5a9ce7853100002a00ab515e")
                .headers(headers)
                .fields(fields)
                .asJson();
        log.info("body:{}",jsonResponse.getBody());
        assertNotNull(jsonResponse.getBody());
        assertEquals(202, jsonResponse.getStatus());
    }

    @Test
    public void givenRequestBodyWhenCreatedThenCorrect() throws UnirestException {

        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://www.mocky.io/v2/5a9ce7663100006800ab515d")
                .body("{\"name\":\"Sam Baeldung\", \"city\":\"viena\"}")
                .asJson();
        assertEquals(201, jsonResponse.getStatus());
    }

    @Test
    public void givenBodyMyMock() throws  UnirestException{
        String jsonBody = "{\n" +
                "\"name\" : \"Slava\",\n" +
                "\"city\" : \"London\"\n" +
                "}";
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://run.mocky.io/v3/cefb03b6-04bf-4f85-ab7f-dc5f5da56853")
            .body(jsonBody)
                .asJson();
        log.info("resp body:{}",jsonResponse.getBody());
        assertEquals(201, jsonResponse.getStatus());
    }

    @Test
    public void givenArticleWhenCreatedThenCorrect() throws UnirestException {
        Article article = new Article("ID1213", "Guide to Rest", "baeldung");
        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://www.mocky.io/v2/5a9ce7663100006800ab515d")
                .body(article) //no ObjectMapper conversion necessary to convert Article class in json!
                .asJson();
        assertEquals(201, jsonResponse.getStatus());
    }

    @Test
    @Ignore
    public void whenAysncRequestShouldReturnOk() throws InterruptedException, ExecutionException {
        Future<HttpResponse<JsonNode>> future = Unirest.post("http://www.mocky.io/v2/5a9ce37b3100004f00ab5154?mocky-delay=1000ms")
                .header("accept", "application/json")
                .asJsonAsync(new Callback<JsonNode>() {

                    public void failed(UnirestException e) {
                        // Do something if the request failed
                        log.info("request failed .. action on fail");
                    }

                    public void completed(HttpResponse<JsonNode> response) {
                        // Do something if the request is successful
                        log.info("request succeeded .. action on success");
                    }

                    public void cancelled() {
                        // Do something if the request is cancelled
                        log.info("request canceled .. action on cancell");

                    }

                });
        assertEquals(200, future.get()
                .getStatus());

    }

}
