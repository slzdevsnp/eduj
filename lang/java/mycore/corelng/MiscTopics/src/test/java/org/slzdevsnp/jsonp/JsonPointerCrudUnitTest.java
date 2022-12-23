package org.slzdevsnp.jsonp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.json.*;
import java.io.FileReader;
import java.io.IOException;

public class JsonPointerCrudUnitTest {

    @Test
    public void testJsonPointerCrudForAddress() {

        JsonPointerCrud jsonPointerCrud =
                new JsonPointerCrud(JsonPointerCrudUnitTest.class
                        .getResourceAsStream("/address.json"));

        assertFalse(jsonPointerCrud.check("city"));

        // insert a value
        jsonPointerCrud.insert("city", "Rio de Janeiro");

        assertTrue(jsonPointerCrud.check("city"));

        // fetch full json
        String fullJSON = jsonPointerCrud.fetchFullJSON();

        assertTrue(fullJSON.contains("name"));

        assertTrue(fullJSON.contains("city"));

        // fetch value
        String cityName = jsonPointerCrud.fetchValueFromKey("city");

        assertEquals(cityName, "Rio de Janeiro");

        // update value
        jsonPointerCrud.update("city", "Sao Paulo");

        // fetch value
        cityName = jsonPointerCrud.fetchValueFromKey("city");

        assertEquals(cityName, "Sao Paulo");

        // delete
        jsonPointerCrud.delete("city");

        assertFalse(jsonPointerCrud.check("city"));

    }

    @Test
    public void testJsonPointerCrudForBooks() {

        JsonPointerCrud jsonPointerCrud = new JsonPointerCrud(JsonPointerCrudUnitTest.class.getResourceAsStream("/books.json"));

        // fetch value
        String book = jsonPointerCrud.fetchListValues("books/1");

        assertEquals(book, "{\"title\":\"Title 2\",\"author\":\"John Doe\"}");

    }

    // NB! this one is important 2nd level elements
    @Test
    public void testJsonPointerFetchField() throws IOException {
        JsonStructure jsonStructure;
        try(JsonReader reader = Json.createReader(JsonPointerCrudUnitTest.class
                        .getResourceAsStream("/books.json"))) {
            jsonStructure = reader.read();
        }

        JsonPointer jsonPointer = Json.createPointer("/library");
        JsonString jsonString = (JsonString) jsonPointer.getValue(jsonStructure);
        assertEquals(jsonString.getString(),"My Personal Library");


        jsonPointer = Json.createPointer("/range");
        JsonStructure jsonSubStruct = (JsonStructure) jsonPointer.getValue(jsonStructure);
        System.out.println(jsonSubStruct.toString());


        JsonString lftJsonString = (JsonString) Json
                                                .createPointer("/from")
                                                .getValue(jsonSubStruct);
        JsonString rghtJsonString = (JsonString) Json
                                                .createPointer("/to")
                                                .getValue(jsonSubStruct);

        assertEquals(lftJsonString.getString(),"left");
        assertEquals(rghtJsonString.getString(),"right");

    }

    @Test
    public void testJsonPointerMissingFiield() throws IOException {
        JsonStructure jsonStructure;
        try(JsonReader reader = Json.createReader(JsonPointerCrudUnitTest.class
                .getResourceAsStream("/books.json"))) {
            jsonStructure = reader.read();
        }

        JsonPointer jsonPointer = Json.createPointer("/missing");

        assertFalse(jsonPointer.containsValue(jsonStructure));
    }

}
