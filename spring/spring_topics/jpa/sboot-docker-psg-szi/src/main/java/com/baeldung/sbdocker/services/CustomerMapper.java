package com.baeldung.sbdocker.services;

import com.baeldung.sbdocker.model.Customer;
import com.baeldung.sbdocker.persistence.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer entityToModel(CustomerEntity entity);


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    CustomerEntity modelToEntity(Customer api);

    List<Customer> entityListToModelList(List<CustomerEntity> entity);
}
