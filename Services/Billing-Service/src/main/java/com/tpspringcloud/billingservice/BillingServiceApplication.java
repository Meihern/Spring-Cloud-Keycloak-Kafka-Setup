package com.tpspringcloud.billingservice;

import com.tpspringcloud.billingservice.entities.Bill;
import com.tpspringcloud.billingservice.entities.ProductItem;
import com.tpspringcloud.billingservice.feignclients.CustomerFeignClient;
import com.tpspringcloud.billingservice.feignclients.ProductFeignClient;
import com.tpspringcloud.billingservice.models.Customer;
import com.tpspringcloud.billingservice.models.Product;
import com.tpspringcloud.billingservice.repositories.BillRepository;
import com.tpspringcloud.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerFeignClient customerFeignClient,
                            ProductFeignClient productFeignClient){
        return args -> {
            Customer customer = customerFeignClient.getCustomerById(1L);
            Bill bill = billRepository.save(new Bill(null, new Date(), null, customer, customer.getId()));
            PagedModel<Product> pageProducts = productFeignClient.pageProducts();
            pageProducts.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setPrice(product.getPrice());
                productItem.setQuantity(1+new Random().nextInt(100));
                productItem.setBill(bill);
                productItem.setProductID(product.getId());
                productItem.setProduct(product);
                productItemRepository.save(productItem);
            });
            System.out.println(customer.toString());
            System.out.println(bill.toString());
        };
    }

}
