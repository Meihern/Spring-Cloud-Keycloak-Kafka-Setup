package com.tpspringcloud.customerservice;

import com.tpspringcloud.customerservice.entities.Customer;
import com.tpspringcloud.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        repositoryRestConfiguration.exposeIdsFor(Customer.class);
        return args -> {
            customerRepository.save(new Customer(null, "youssef", "achir.youssef97@gmail.com"));
            customerRepository.save(new Customer(null, "ouiam", "ouiamkhattach@gmail.com"));
            customerRepository.save(new Customer(null, "oussama", "oussamaaitalla@gmail.com"));
            customerRepository.save(new Customer(null, "hajar", "hajarlablaoui@gmail.com"));
            customerRepository.save(new Customer(null, "yasser", "yasserchihab@gmail.com"));
            customerRepository.findAll().forEach(customer -> {
                System.out.println(customer.toString());
            });
        };
    }

}
