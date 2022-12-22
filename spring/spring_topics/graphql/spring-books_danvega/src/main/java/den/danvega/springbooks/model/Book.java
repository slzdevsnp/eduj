package den.danvega.springbooks.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)

public class Book {
    Integer id;
    String title;
    Integer pages;
    Rating rating;
    Author author;
}
