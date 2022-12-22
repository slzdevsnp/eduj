package com.baeldung.sbdocker.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


//public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByCustomerId(int customerId);


}
