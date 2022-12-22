package com.baeldung.sbdocker.services;

import com.baeldung.sbdocker.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CustomerService {


    /**
     * Sample usage:
     *
     * curl -X POST $HOST:$PORT/customer \
     *   -H "Content-Type: application/json" --data \
     *   '{"firstName":"John","lastName":"Doe"}'
     *
     * @param body
     * @return
     */

    @PostMapping(value = "/customer",
            consumes = "application/json",
            produces = "application/json")
    Customer createCustomer(@RequestBody Customer body);

    /**
     * Sample usage: curl $HOST:$PORT/customer/1
     *
     * @param customerId
     * @return the product, if found, else null
     */
    @GetMapping(value = "/customer/{customerId}",
            produces = "application/json")
    Customer getCustomer(@PathVariable int customerId);


    /**
     * Sample usage:
     *
     * curl -X GET $HOST:$PORT/customers
     *
     */
    @GetMapping(value = "/customers",
            produces = "application/json")
    List<Customer> getCustomers();


    /**
     * Sample usage:
     *
     * curl -X DELETE $HOST:$PORT/customer/1
     *
     * @param customerId
     */
    @DeleteMapping(value = "/customer/{customerId}")
    void deleteCustomer(@PathVariable int customerId);

}
