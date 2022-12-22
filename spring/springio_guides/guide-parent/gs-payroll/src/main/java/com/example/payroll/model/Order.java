package com.example.payroll.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ORDER")
@NoArgsConstructor
@Data
public class Order {

    private @Id @GeneratedValue Long id;
    private String description;
    private Status status;

    public Order(String descrption, Status status){
        this.description = descrption;
        this.status = status;
    }
}


