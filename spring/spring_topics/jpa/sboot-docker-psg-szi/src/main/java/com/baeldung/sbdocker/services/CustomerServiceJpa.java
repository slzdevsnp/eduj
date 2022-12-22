package com.baeldung.sbdocker.services;

import com.baeldung.sbdocker.model.Customer;
import com.baeldung.sbdocker.persistence.CustomerEntity;
import com.baeldung.sbdocker.persistence.CustomerRepository;
import com.baeldung.sbdocker.util.exceptions.InvalidInputException;
import com.baeldung.sbdocker.util.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CustomerServiceJpa implements CustomerService {

    Logger LOG = LoggerFactory.getLogger(CustomerServiceJpa.class);

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerServiceJpa(CustomerRepository repository,
                              CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Customer createCustomer(Customer body){
        try {
            CustomerEntity entity = mapper.modelToEntity(body);
            CustomerEntity newEntity = repository.save(entity);
            LOG.debug("createCustomer: created a customer entity: {} {} {}",
                    body.getCustomerId(), body.getFirstName(), body.getLastName());
            return mapper.entityToModel(newEntity);
        } catch (DataIntegrityViolationException dive) {
            throw new InvalidInputException("Duplicate key, customer Id: "
                    + body.getCustomerId() + ", first name:" + body.getFirstName() + " last name:" + body.getLastName());
        }
    }

    @Override
    public Customer getCustomer(int customerId){
        if (customerId < 1) throw new InvalidInputException("Invalid customerId: " + customerId);
        CustomerEntity entity = repository.findByCustomerId(customerId)
                .orElseThrow(() -> new NotFoundException("no customer found for  customerId: " + customerId));
        Customer response = mapper.entityToModel(entity);
        LOG.debug("getCustomer: found a customer entity: {} {} {}",
                response.getCustomerId(), response.getFirstName(), response.getLastName());
        return response;
    }

    @Override
    public List<Customer> getCustomers(){

        List<CustomerEntity> entities = (List<CustomerEntity>) repository.findAll();
        if(entities.isEmpty()){
            throw new NotFoundException("No customers found at all");
        }
        List<Customer>  response = mapper.entityListToModelList(entities);

        LOG.debug("getCustomers: found {} customer entities", response.size());
        return response;
    }

    @Override
    public void deleteCustomer(int customerId){
        if (customerId < 1) throw new RuntimeException("Invalid customerId: " + customerId);
        repository.findByCustomerId(customerId).ifPresent(e -> repository.delete(e));
        LOG.debug("deleteCustomer: deleted a customer entity: {}", customerId);
    }


}
