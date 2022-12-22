package com.baeldung.sbdocker.persistence;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "customer",
    indexes = { @Index(name = "customer_unique_idx", unique = true, columnList = "first_name,last_name")})
@Data
@NoArgsConstructor
@ToString
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    public CustomerEntity(Integer customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
