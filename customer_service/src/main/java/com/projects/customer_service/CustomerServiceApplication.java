package com.projects.customer_service;

import com.projects.customer_service.entities.Customer;
import com.projects.customer_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            customerRepository.saveAll(List.of(
                    Customer.builder().name("customer1").email("customer1@gmail.com")
                            .phone("0612345678").build(),
                    Customer.builder().name("customer2").email("customer2@gmail.com")
                            .phone("0645678923").build(),
                    Customer.builder().name("customer3").email("customer3@gmail.com")
                            .phone("0681234567").build()
            ));

//            customerRepository.findAll().forEach(System.out::println);
        };
    }

}
