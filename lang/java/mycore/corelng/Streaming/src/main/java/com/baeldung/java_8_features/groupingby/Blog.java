package com.baeldung.java_8_features.groupingby;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data

@ToString
public class Blog {
    private String authorName;
    private List<String> comments;

    public Blog(String authorName, String... comments) {
        this.authorName = authorName;
        this.comments = List.of(comments);
    }

}
