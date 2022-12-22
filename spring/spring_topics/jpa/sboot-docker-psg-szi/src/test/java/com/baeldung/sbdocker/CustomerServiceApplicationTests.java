package com.baeldung.sbdocker;

import com.baeldung.sbdocker.model.Customer;
import com.baeldung.sbdocker.persistence.CustomerEntity;
import com.baeldung.sbdocker.persistence.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static reactor.core.publisher.Mono.just;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:h2:mem:compose-postgres"})
public class CustomerServiceApplicationTests {

    @Autowired
    private WebTestClient client;

    @Autowired
    private CustomerRepository repository;

    @Before
    public void setupDb() {
        repository.deleteAll();
    }

    @Test
    public void getCustomerById() {
        int customerId = 1;
        Customer customer = new Customer(customerId, "John", "Doe");
        postAndVerifyCustomer(customer, OK);

        assertTrue(repository.findByCustomerId(customerId).isPresent());

        getAndVerifyCustomer(customerId,OK)
                .jsonPath("$.customerId").isEqualTo(customerId)
                .jsonPath("$.firstName").isEqualTo("John");
    }


    @Test
    public void getAllCustomers(){
        List<Customer> customers = Arrays.asList(
                new Customer(1, "John", "Doe"),
                new Customer(2, "Jane", "Doe"),
                new Customer(3, "Jack", "Damn")
        );
        customers.forEach(c -> postAndVerifyCustomer(c,OK));

        List<CustomerEntity> result = new ArrayList<>();
        repository.findAll().forEach(c -> result.add(c));
        assertEquals(customers.size(), result.size());

        getAndVerifyCustomers(OK)
                .jsonPath("$.length()").isEqualTo(customers.size())
                .jsonPath("$[0].customerId").isEqualTo(1)
                .jsonPath("$[0].firstName").isEqualTo("John");

        //wipe all and try to fetch customers
        repository.deleteAll();
        getAndVerifyCustomers(NOT_FOUND);


    }

    @Test
    public void duplicateError(){

        int customerId = 1;
        Customer customer = new Customer(customerId, "John", "Doe");
        postAndVerifyCustomer(customer, OK); //1st save
        assertTrue(repository.findByCustomerId(customerId).isPresent());
        //2nd safe should fail
        postAndVerifyCustomer(customer, UNPROCESSABLE_ENTITY)
                .jsonPath("$.path").isEqualTo("/customer");

    }

    @Test
    public void deleteCustomer(){

        int customerId = 1;
        Customer customer = new Customer(customerId, "John", "Doe");
        postAndVerifyCustomer(customer, OK);
        deleteAndVerifyProduct(customerId, OK);
        //2nd delete should pass
        deleteAndVerifyProduct(customerId, OK);
        assertTrue(repository.findByCustomerId(customerId).isEmpty());
    }


    private WebTestClient.BodyContentSpec postAndVerifyCustomer(Customer customer, HttpStatus expectedStatus){

        WebTestClient.BodyContentSpec result  =
        client.post()
                .uri("/customer")
                .body(just(customer), Customer.class)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus)
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody();
        return result;
    }

    private WebTestClient.BodyContentSpec getAndVerifyCustomer(int customerId, HttpStatus expectedStatus){
        return  getAndVerifyCustomer("/" + String.valueOf(customerId), expectedStatus);
    }

    private WebTestClient.BodyContentSpec getAndVerifyCustomer(String customerIdPath, HttpStatus expectedStatus) {
        return client.get()
                .uri("/customer" + customerIdPath)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus)
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody();
    }

    private WebTestClient.BodyContentSpec getAndVerifyCustomers(HttpStatus expectedStatus) {
        return client.get()
                .uri("/customers")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus)
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody();
    }

    private WebTestClient.BodyContentSpec deleteAndVerifyProduct(int customerId, HttpStatus expectedStatus) {
        return client.delete()
                .uri("/customer/" + customerId)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus)
                .expectBody();
    }

}
