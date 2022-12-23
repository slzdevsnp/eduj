package org.slzdevsnp.string;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.*;

public class TestStringStream {

    @Test
    public void testStringToMap(){

        String[] programming_languages = new String[] {"language:java","os:linux","editor:emacs"};

        Map<String,String> expectation=new HashMap<>();
        expectation.put("language", "java");
        expectation.put("os", "linux");
        expectation.put("editor", "emacs");

        Map<String,String> result = Arrays.asList(programming_languages)
                .stream()
                .map(s -> s.split(":"))
                .collect(toMap(s -> s[0], s->s[1]));
        assertEquals(result,expectation);
    }

    @Test
    public void testRemoveLeadingTrailingChar(){
        final String example = "[{\"age\":101}]";
        final String expectation = "{\"age\":101}";
        //1
        String result1 = example.substring(1,example.length()-1);
        assertEquals(result1, expectation);
        //2
        String result2 = example
                .replaceAll("^\\[{1}","")
                .replaceAll("\\]{1}$","");

        System.out.println("result2:"+result2);
        assertEquals(result2, expectation);

    }

}
