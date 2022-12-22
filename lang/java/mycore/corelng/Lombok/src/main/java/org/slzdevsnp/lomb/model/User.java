package org.slzdevsnp.lomb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
public class User implements Serializable {

    private String firstName;
    private String lastName;
    private int age;

    //constructor
    public User(String firstName, String lastName, int age) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    // NB!! we do not specify getters and setters

}
