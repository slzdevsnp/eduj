package com.baeldung.sbdocker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

    private Integer customerId;
    private String firstName;
    private String lastName;

    public Customer(){

        firstName = null;
        lastName = null;
    }
}
