package com.baeldung.java_8_features.groupingby;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class StreamFilterTest {

    @Data
    @AllArgsConstructor
    @ToString
    class Customer {
        private String name;
        private int points;

        public boolean hasOverHundredPoints() {
            return (points > 100) ? true : false;
        }
    }


    private List<Customer> customers;

    @Before
    public void init() {
        Customer john = new Customer("John P.", 15);
        Customer sarah = new Customer("Sarah M.", 200);
        Customer charles = new Customer("Charles B.", 150);
        Customer mary = new Customer("Mary T.", 1);
        customers = Arrays.asList(john, sarah, charles, mary);
    }

    @Test
    public void simpleFilter() {
        List<Customer> fatCustomers = customers.stream()
                .filter(c -> c.getPoints() > 100)
                .collect(Collectors.toList());
        assertThat(fatCustomers.size(), is(2));
    }

    @Test
    public void multiCriteriaFilter() {
        List<Customer> fatCustomers = customers.stream()
                .filter(c -> c.getPoints() > 100 && c.getName().startsWith("Charles"))
                .collect(Collectors.toList());
        assertThat(fatCustomers.size(), is(1));
    }


    @Test
    public void filterWithObjectMethod() {
        List<Customer> fatCustomers = customers.stream()
                .filter(Customer::hasOverHundredPoints)
                .collect(Collectors.toList());
        assertThat(fatCustomers.size(), is(2));
    }


}
