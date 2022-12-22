package com.example.payroll.config;

import com.example.payroll.model.Employee;
import com.example.payroll.model.Order;
import com.example.payroll.model.Status;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner intiDatabase(EmployeeRepository employeeRepository,
                                   OrderRepository orderRepository){
        return args -> {
            // szi changed constructor of Employee(name,role) to Employee(firstName, lastName, role)
            employeeRepository.save(new Employee("Bilbo", "Baggins","burglar"));
            employeeRepository.save(new Employee("Frodo", "Baggins","thief"));
            employeeRepository.findAll().forEach((employee -> log.info("preloaded - {}",employee)));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded  - {}",order);
            });
        };
    }

}
