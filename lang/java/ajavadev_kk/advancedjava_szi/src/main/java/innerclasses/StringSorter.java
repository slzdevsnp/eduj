package innerclasses;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StringSorter {
    private List<String> strings;

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public List<String> naturalSort() {
        Collections.sort(strings);
        return strings;
    }

    public List<String> naturalSortWithStreams() {
        return strings.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    //inner method in interface Comparator
    public List<String> lengthSort() {
        Collections.sort(strings,
                        new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        return strings;
    }
    //second param in sor() is a lambda function
    public List<String> lengthSortWithLambda() {
        Collections.sort(strings, (s1, s2) -> s1.length() - s2.length());
        return strings;
    }

    //using a stream based implementation
    public List<String> lengthReverseSortWithStreams() {
        return strings.stream()
                //implementation of comparator in lambda
                .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length()))
                .collect(Collectors.toList());
    }
}
