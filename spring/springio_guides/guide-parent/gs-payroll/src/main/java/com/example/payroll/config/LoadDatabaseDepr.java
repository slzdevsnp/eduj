package com.example.payroll.config;

import com.example.payroll.model.Employee;
import com.example.payroll.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.CommandLinePropertySource;

@Slf4j
//@Configuration
public class LoadDatabaseDepr {

    @Bean
    CommandLineRunner intiDatabase(EmployeeRepository repository){
        return args -> {
            // szi changed constructor of Employee(name,role) to Employee(firstName, lastName, role)
         log.info("Preloading {}", repository.save(new Employee("Bilbo", "Baggins","burglar")));
         log.info("Preloading {}", repository.save(new Employee("Frodo", "Baggins","thief")));

        };
    }

}
