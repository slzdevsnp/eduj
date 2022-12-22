package com.baeldung.sbdocker;


import com.baeldung.sbdocker.persistence.CustomerEntity;
import com.baeldung.sbdocker.persistence.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = NOT_SUPPORTED)
public class PersistenceTests {

    @Autowired
    private CustomerRepository repository;

    private CustomerEntity savedEntity;

    @Before
    public void setupDb() {
        repository.deleteAll();

        CustomerEntity entity = new CustomerEntity(1,"John", "Doe");
        savedEntity = repository.save(entity);

        assertEqualsCustomer(entity, savedEntity);
    }

    @Test
    public void create() {

        CustomerEntity newEntity = new CustomerEntity(2, "Jane", "Doe");
        repository.save(newEntity);

        CustomerEntity foundEntity = repository
                .findById( newEntity.getId()).get();
        assertEqualsCustomer(newEntity, foundEntity);
        assertEquals(2, repository.count());
    }

    @Test
    public void update() {
        savedEntity.setFirstName("Alex");
        repository.save(savedEntity);
        CustomerEntity foundEntity = repository
                .findById(savedEntity.getId()).get();

        assertEqualsCustomer(savedEntity, foundEntity);
        assertEquals("Alex", foundEntity.getFirstName());
    }

    @Test
    public void delete() {
        repository.delete(savedEntity);
        assertEquals(0, repository.count());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createDuplicateError() {
        CustomerEntity newEntity = new CustomerEntity(1, "John", "Doe");
        repository.save(newEntity);
    }


    @Test
    public void optimisticLockError() {
        CustomerEntity entity1 = repository.findById(savedEntity.getId()).get();
        CustomerEntity entity2 = repository.findById(savedEntity.getId()).get();

        entity1.setFirstName("JohnA");
        repository.save(entity1);
        //update the entity using the 2nd entity object
        try {
            entity2.setFirstName("JohnB");
            repository.save(entity2);
            fail("expected optimistic lock Exception");
        } catch (OptimisticLockingFailureException e) {
        }
        //get updated entity from database and verify its new state
        CustomerEntity updatedEntity = repository.findById(savedEntity.getId()).get();
        assertEquals("JohnA", updatedEntity.getFirstName());
        assertEquals(1, (long)updatedEntity.getVersion());

    }


    private void assertEqualsCustomer(CustomerEntity expected, CustomerEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
    }

}
