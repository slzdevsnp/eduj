package org.slzdevsnp.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestXmlToJson {


    @Test
    void givenSimpleXmlTestJson() throws JsonProcessingException {
        String flowerXML = "<Flower><name>Poppy</name><color>RED</color><petals>9</petals></Flower>";
        XmlMapper xmlMapper = new XmlMapper();
        Flower poppy = xmlMapper.readValue(flowerXML, Flower.class);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(poppy);
        System.out.println(String.format("json string: %s",json));
        assertTrue(json.length()>0);
    }

    @Test
    void givenSimpleXmlFileTestJson() throws IOException {

        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("flower.xml");

        String xmlContent = readFromInputStream(is);
        XmlMapper xmlMapper = new XmlMapper();
        Flower poppy = xmlMapper.readValue(xmlContent, Flower.class);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(poppy);
        System.out.println(String.format("json string: %s",json));
        assertEquals(json, "{\"name\":\"Poppy\",\"color\":\"RED\",\"petals\":9}");
    }


    @Test
    void givenSimpleXmlParseWithTreeTraversal() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("flower.xml");
        String xmlContent = readFromInputStream(is);
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(xmlContent.getBytes());
        //
        ObjectMapper jsonMapper = new ObjectMapper();
        String json = jsonMapper.writeValueAsString(node);
        System.out.println(String.format("json from treeTraversal: %s",json));
        assertTrue(json.length()>0);
    }

    @Test
    void givenBigXmlParsewithTreeTraversal() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("ContractInfoRprt.xml");
        String xmlContent = readFromInputStream(is);
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(xmlContent.getBytes());
        //
        ObjectMapper jsonMapper = new ObjectMapper();
        String json = jsonMapper.writeValueAsString(node);
        System.out.println(String.format("%s",json));
        assertTrue(json.length()>0);

    }

    //helper method
    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }


}
