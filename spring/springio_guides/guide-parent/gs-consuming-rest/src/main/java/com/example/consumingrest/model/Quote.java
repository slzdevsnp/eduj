package com.example.consumingrest.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;



@NoArgsConstructor
@Getter
@ToString
@JsonIgnoreProperties  //any propereties not bound in this type should be ignored
public class Quote {
    private String type;
    private Value value;
}
