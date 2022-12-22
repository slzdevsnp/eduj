package com.baeldung.java_8_features.groupingby;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BlogPostTypeAuthorTuple {
    private final BlogPostType type;
    private final String author;

}
