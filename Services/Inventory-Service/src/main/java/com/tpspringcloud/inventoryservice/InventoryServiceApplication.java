package com.tpspringcloud.inventoryservice;

import com.tpspringcloud.inventoryservice.entities.Product;
import com.tpspringcloud.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        repositoryRestConfiguration.exposeIdsFor(Product.class);
        return args -> {
            productRepository.save(new Product(null, "Nintendo Switch", 4000, 10));
            productRepository.save(new Product(null, "Play Station 5", 5500, 3));
            productRepository.save(new Product(null, "Play Station 4", 3200, 50));
            productRepository.save(new Product(null, "Mac", 15000, 5));
            productRepository.save(new Product(null, "MSI", 10900, 7));
            productRepository.findAll().forEach(product -> {
                System.out.println(product.toString());
            });
        };
    }

}
