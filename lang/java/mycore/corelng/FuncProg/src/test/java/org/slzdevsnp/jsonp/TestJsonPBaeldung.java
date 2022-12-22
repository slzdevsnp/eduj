package org.slzdevsnp.jsonp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestJsonPBaeldung {

    static String filename = "/tmp/file.json";

    @BeforeAll
    public static void init() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("{\n");
            writer.write("\t\"library\":\"My Personal Library\",\n");
            writer.write("\t\"books\":[\n");
            writer.write("\t\t{\"title\":\"Title 1\",\"author\":\"Marshak Samu\"},\n");
            writer.write("\t\t{\"title\":\"Title 2\",\"author\":\"Marx Karl\"}\n");
            writer.write("\t]\n");
            writer.write("\t\"return_in_days\":21\n");
            writer.write("}\n");
        }
    }

    @Test
    void testJson() throws JsonException {

        String json = "{\"id\": 123456, \"age\" : 10, \"title\": \"Fun with JSON-Processing\", \"published\": true}";
        System.out.println("input json:" + json);
        // helper structure
        List<Integer> ilst = new ArrayList<>();


        try (JsonReader jsonReader = Json
                .createReader(new StringReader(json))) {

            JsonObject jsonObject = jsonReader.readObject();

            assertEquals(jsonObject.getInt("id"), 123456);
            assertEquals(jsonObject.getString("title"), "Fun with JSON-Processing");
            assertTrue(jsonObject.getBoolean("published"));

            // String snullabl = jsonObject.getString("missing_t"); //this approach produces null exception
            //Optional<String> opt =  Optional.ofNullable(snullabl);
            //assertTrue(opt.isEmpty());

            //tag not present NB works
            Optional<JsonString> mx = Optional
                    .ofNullable(jsonObject.getJsonString("missingtag"));
            assertTrue(mx.isEmpty());
            mx.ifPresent(v -> ilst.add(Integer.parseInt(v.toString())));

            // .getValue() does not supported to be null
            // produces JsonException Non-existing name/value pair in the object
            //assertTrue( Optional.ofNullable(jsonObject
            //        .getValue("/missingtag"))
            //        .isEmpty());


            //present  .getValue() needs pointer notation eg "/id"
            // pointer notation tag needs to be present
            // but we do not care about the field type!!
            Optional<JsonValue> px = Optional
                    .ofNullable(jsonObject.getValue("/id"));
            px.ifPresent(v -> ilst.add(Integer.valueOf(((JsonNumber) v).intValue() )));


//            Optional<JsonString> ppx = Optional
//                    .ofNullable(jsonObject.getJsonString("id"));  // produces exception
//            ppx.ifPresent(v->ilst.add(Integer.parseInt(v.toString())));

            // works
            Optional<JsonNumber> ppx = Optional
                    .ofNullable(jsonObject.getJsonNumber ("id"));  // produces exception
            ppx.ifPresent(v->ilst.add(Integer.parseInt(v.toString())));

           //works
            Optional<JsonNumber> nx = Optional
                    .ofNullable(jsonObject.getJsonNumber("age"));
            assertTrue(nx.isPresent());
            nx.ifPresent(v -> ilst.add(Integer.parseInt(v.toString())));

            // 3 elemns is expected
            assertEquals(ilst.size(), 3);

            for (Integer el : ilst) {
                System.out.println("el:" + el);
            }
        }
    }

    @Test
    void testMultiJson() throws JsonException {

        String json = "{\"id\": 123456,   \"range\": {\"from\":\"2020-01-01\", \"to\":\"2020-01-02\"}}";
        System.out.println("input json:" + json);

        try (JsonReader jsonReader = Json
                .createReader(new StringReader(json))) {

            JsonObject jsonObject = jsonReader.readObject();

            //assertEquals(jsonObject.getValue("/range").toString(), "2020-01-01");

        }

    }

}
