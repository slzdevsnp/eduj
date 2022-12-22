package com.example.payroll.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmployeeDepr {

    private @Id @GeneratedValue Long id;  //pk
    private String name; //attributes
    private String role;
    //constructor with all attributes but no PK
    public EmployeeDepr(String name, String role){
        this.name = name;
        this.role = role;
    }
}
