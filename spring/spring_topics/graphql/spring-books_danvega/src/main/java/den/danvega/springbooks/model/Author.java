package den.danvega.springbooks.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Author {
    Integer id;
    String firstName;
    String lastName;

    public String fullName() {
        return firstName + " " + lastName;
    }
}
