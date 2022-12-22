package org.slzdev.generics.model;

import lombok.*;


@Data
public class Student extends Person{
    private String university;


    public Student(String name, int age, String university){
        super(name, age);
        this.university = university;
    }

}