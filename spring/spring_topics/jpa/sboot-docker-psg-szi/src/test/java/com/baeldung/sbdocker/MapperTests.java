package com.baeldung.sbdocker;

import com.baeldung.sbdocker.model.Customer;
import com.baeldung.sbdocker.persistence.CustomerEntity;
import com.baeldung.sbdocker.services.CustomerMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.*;

public class MapperTests {

    private CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    public void mapperTests(){
        assertNotNull(mapper);

        Customer  cust_one = new Customer(1, "John", "Doe");
        CustomerEntity entity = mapper.modelToEntity(cust_one);
        assertEquals(cust_one.getCustomerId(), entity.getCustomerId());
        assertEquals(cust_one.getFirstName(), entity.getFirstName());
        assertEquals(cust_one.getLastName(), entity.getLastName());

        Customer cust_two = mapper.entityToModel(entity);
        assertEquals(cust_one.getCustomerId(), cust_two.getCustomerId());
        assertEquals(cust_one.getFirstName(), cust_two.getFirstName());
        assertEquals(cust_one.getLastName(), cust_two.getLastName());
    }

}
