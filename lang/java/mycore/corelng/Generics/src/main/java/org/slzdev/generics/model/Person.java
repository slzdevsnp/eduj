package org.slzdev.generics.model;


import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public   class Person{
    private String name;
    private Integer age;
}
