package com.baeldung.java_8_features.groupingby;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;



@Data
@ToString
@AllArgsConstructor
public class BlogPost {

    private String title;
    private String author;
    private BlogPostType type;
    private int likes;
}
