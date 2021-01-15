package com.tpspring.ecommerce;

import com.tpspring.ecommerce.entities.Product;
import com.tpspring.ecommerce.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            productRepository.save(new Product(null, "Nintendo Switch", 3250));
            productRepository.save(new Product(null, "Play Station 5", 10250));
            productRepository.save(new Product(null, "Xbox Series X", 9500));
            productRepository.save(new Product(null, "Iphone X", 9000));
        };
    }

}
