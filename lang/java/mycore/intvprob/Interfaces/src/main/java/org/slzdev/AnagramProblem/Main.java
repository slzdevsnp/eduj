package org.slzdev.AnagramProblem;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Main {

    static boolean areAnagramsSortedArrays(String str1, String str2) {
        // Remove spaces and convert strings to lower case
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // If the strings have different length, they are not anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert strings to character arrays
        char[] str1Array = str1.toCharArray();
        char[] str2Array = str2.toCharArray();

        // Sort the character arrays
        Arrays.sort(str1Array);
        Arrays.sort(str2Array);

        // Compare sorted strings
        for (int i = 0; i < str1Array.length; i++) {
            if (str1Array[i] != str2Array[i]) {
                return false;
            }
        }
        return true;
    }

    static boolean areAnagramsFrequencyCounter(String str1, String str2) {
        // Remove spaces and convert strings to lower case
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();
        if (str1.length() != str2.length()) {
            return false;
        }
            Map<Character, Integer> countMap1 = getCharacterCountMap(str1);
            Map<Character, Integer> countMap2 = getCharacterCountMap(str2);

            return countMap1.equals(countMap2);
        }
    static Map<Character, Integer> getCharacterCountMap(String str) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        return countMap;
    }

    public static void main(String[] args) {
        String str1 = "public relations";
        String str2 = "crap built on lies";

        log.info("## implementation sorted arrays..");
        log.info("string: {} and string: {} are anagrams? {}", str1, str2,
         areAnagramsSortedArrays(str1,str2));

        log.info("## implementaion Char Frequency Counter..");
        log.info("string: {} and string: {} are anagrams? {}", str1, str2,
                areAnagramsFrequencyCounter(str1,str2));
    }
}
