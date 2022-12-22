package com.example.payroll.model;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Employee {
    private @Id @GeneratedValue Long id;  //pk
    private String firstName;
    private String lastName;
    private String role;

    //constructor with all attributes but no PK
    public Employee(String firstName, String lastName, String role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    //implement getter and setter for "old version" attribute
    public String getName(){
        return this.firstName + " " + this.lastName;
    }
    public void setName(String name){
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }
}
