package org.slzdevsnp.jackson;


import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

// bean used in json marshalling test
class Student {
    private String name;
    private int age;

    public Student(){}
    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String toString(){
        return "Student [ name: "+name+", age: "+ age+ " ]";
    }
}




class Address{
    private String name;
    private String street_name;
    public Address(){}

    public String getName() {
        return name;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    @Override
    public String toString() {
        return "Address:" +
                " name=" + name +
                " street_name=" + street_name;
    }
}



public class TestJacksonTutorialspoint {

    static final String jsonString1  = "{\"name\":\"Mahesh\", \"age\":21}";
    static final String jsonStudent = "{\"name\":\"Mahesh Kumar\",  \"age\":21,\"verified\":false,\"marks\": [100,90,85],\"range\":{\"left\":11.11,\"right\":19.99}}";


    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Test
    void testMarshallStudentInst() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        //deserialized json --> object
        Student stud = mapper.readValue(jsonString1,Student.class);
        System.out.println(stud.toString());
        assertTrue(stud.toString().length() > 0);
        //serialize object  -->  json
        String jsonStringIn = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(stud);
        System.out.println(jsonStringIn);
        assertTrue(jsonStringIn.startsWith("{"));
    }

    @Test
    void testUnmarshallSerializeToJson() throws JsonProcessingException {
        ObjectMapper  mapper =  TestJacksonTutorialspoint.createObjectMapper();
        Student stud1 =  new Student("Lucas", 21);
        Student stud2 = new Student("Michael", 22);
        List<Student> studs = new ArrayList<>();
        studs.add(stud1);
        studs.add(stud2);
        String jsonString1 = mapper.writeValueAsString(stud1);
        System.out.println("student 1 as : " + jsonString1);
        assertTrue(jsonString1.length() > 0);
        String jsonStringLst = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(studs);
        System.out.println("student list  as json: " + jsonStringLst);
        assertTrue(jsonStringLst.length() > 0);

    }

    @Test
    void testSerialize2Json() {
        ObjectMapper  mapper =  TestJacksonTutorialspoint.createObjectMapper();
        Student stud1 =  new Student("Lucas", 21);
        Student stud2 = new Student("Michael", 22);
        List<Student> studs = new ArrayList<>();
        studs.add(stud1);
        studs.add(stud2);
        String jsonContents = serialize2Json(mapper, studs);
        System.out.println("json str: "+jsonContents);
        assertTrue(jsonContents.length() > 0);
    }

    private String serialize2Json(ObjectMapper mapper, Object o)  {
        String result = null;
        try {
            result = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            System.out.println("Json Processing exception " + e.getMessage());
        }
        return result;
    }

    @Test
    void TestAgainSeriaizationDecerializationFromFile() throws JsonProcessingException, IOException{
        Student stu = new Student();
        stu.setName("Ganesh");
        stu.setAge(23);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("/tmp/student.json"),stu);

        try(Scanner scanner = new Scanner(new File("/tmp/student.json"))){
            String contents = scanner.useDelimiter("\\A").next();
            System.out.println("scanner contents:"+contents);
            assertTrue(contents.length()>0);
        }

        Student studAck = mapper.readValue(new File("/tmp/student.json"),Student.class);
        System.out.println("studAck:"+studAck.toString());
        assertTrue(studAck.toString().length()>0);
    }





    @Test
    void TestSerializationFromString() throws JsonProcessingException, IOException{
        Student stu = new Student();
        stu.setName("Ganesh");
        stu.setAge(23);

        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        mapper.writeValue(bout,stu);
        String json = bout.toString("UTF-8");
        System.out.println(json);
        assertTrue(json.length()>0);

        Address adr = new Address();
        adr.setName("Vladivostok");
        adr.setStreet_name("Lenina Str");
        bout.reset();
        mapper.writeValue(bout,adr);
        String json1 = bout.toString("UTF-8");
        System.out.println(json1);
    }

    @Test
    void testDeseriailzedFromFile() throws JsonProcessingException,IOException{
        try(InputStream is = TestJacksonTutorialspoint.class
                .getResourceAsStream("/address.json");Scanner scanner = new Scanner(is)){
            String contents = scanner.useDelimiter("\\A").next();
            System.out.println(contents);

            ObjectMapper mapper = new ObjectMapper();
            //deserialize: json -> object
            Address adr = mapper.readValue(contents, Address.class);
            System.out.println(adr);
            assertEquals(adr.getName(),"Customer 01");
        }
    }


    @Test
    void testTreeModel() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonStudent);

        JsonNode nameNode = rootNode.path("name");
        System.out.println("Name: "+ nameNode.textValue());
        assertEquals(nameNode.textValue(),"Mahesh Kumar");

        JsonNode ageNode = rootNode.path("age");
        System.out.println("Age: " + ageNode.intValue());
        assertEquals(ageNode.intValue(), 21);

        JsonNode verifiedNode = rootNode.path("verified");
        System.out.println("Verified: " + (verifiedNode.booleanValue() ? "Yes":"No"));
        assertEquals(verifiedNode.booleanValue(),false);

        JsonNode marksNode = rootNode.path("marks");
        Iterator<JsonNode> iterator = marksNode.elements();
        System.out.print("Marks: [ ");

        //array
        while (iterator.hasNext()) {
            JsonNode marks = iterator.next();
            System.out.print(marks.intValue() + " ");
        }
        System.out.println("]");

        //2nd level
        JsonNode rng = rootNode.get("range");
        System.out.println(rng.toPrettyString());
        JsonNode lft= rng.get("left");
        JsonNode rght = rng.get("right");
        System.out.println("range left:"+lft.doubleValue() + " right:"+rght.doubleValue());
        assertEquals(rootNode.get("range").get("left").doubleValue(),11.11);
        assertEquals(rootNode.get("range").get("right").doubleValue(),19.99);

        //missing element
        JsonNode missedEl = rootNode.get("missing");
        System.out.println(Optional.ofNullable(missedEl));
        Optional<JsonNode> omiss= Optional.ofNullable(rootNode.get("missing"));
        assertTrue(omiss.isEmpty());

    }


    @Test
    void testStreamingWrtingApi() throws JsonProcessingException,IOException{

        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jsonGenerator = jsonFactory
                .createGenerator(new File("/tmp/student1.json"), JsonEncoding.UTF8);

        jsonGenerator.writeStartObject();
        // "name" : "Mahesh Kumar"
        jsonGenerator.writeStringField("name", "Mahesh Kumar");
        // "age" : 21
        jsonGenerator.writeNumberField("age", 21);
        // "verified" : false
        jsonGenerator.writeBooleanField("verified", false);
        // "marks" : [100, 90, 85]
        jsonGenerator.writeFieldName("marks");
        // [
        jsonGenerator.writeStartArray();
        // 100, 90, 85
        jsonGenerator.writeNumber(100);
        jsonGenerator.writeNumber(90);
        jsonGenerator.writeNumber(85);
        // ]
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
        jsonGenerator.close();

        //read from freshly crated file
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> dataMap = mapper.readValue(new File("/tmp/student1.json"), Map.class);

        System.out.println(dataMap.get("name"));
        System.out.println(dataMap.get("age"));
        System.out.println(dataMap.get("verified"));
        System.out.println(dataMap.get("marks"));

        assertTrue(new File("/tmp/student1.json").exists());
    }

    @Test
    void testStreamingReadingApi()throws JsonProcessingException,IOException{

        //make json file
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(new File(
                "/tmp/student2.json"), JsonEncoding.UTF8);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", "Mahesh Kumar");
        jsonGenerator.writeNumberField("age", 21);
        jsonGenerator.writeBooleanField("verified", false);
        jsonGenerator.writeFieldName("marks");
        jsonGenerator.writeStartArray(); // [
        jsonGenerator.writeNumber(100);
        jsonGenerator.writeNumber(90);
        jsonGenerator.writeNumber(85);
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
        jsonGenerator.close();


        //parsing
        JsonParser jsonParser = jsonFactory.createParser(new File("/tmp/student2.json"));
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            //get the current token
            String fieldname = jsonParser.getCurrentName();

            if ("name".equals(fieldname)) {
                //move to next token
                jsonParser.nextToken();
                System.out.println(jsonParser.getText());
            }
            if("age".equals(fieldname)){
                //move to next token
                jsonParser.nextToken();
                System.out.println(jsonParser.getNumberValue());
            }
            if("verified".equals(fieldname)){
                //move to next token
                jsonParser.nextToken();
                System.out.println(jsonParser.getBooleanValue());
            }
            if("marks".equals(fieldname)){
                //move to [
                jsonParser.nextToken();
                // loop till token equal to "]"
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    System.out.println(jsonParser.getNumberValue());
                }
            }
        }

        assertTrue(new File("/tmp/student2.json").exists());
    }


}
