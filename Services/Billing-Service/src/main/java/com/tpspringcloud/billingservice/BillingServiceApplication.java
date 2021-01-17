package com.tpspringcloud.billingservice;

import com.tpspringcloud.billingservice.entities.Bill;
import com.tpspringcloud.billingservice.entities.ProductItem;
import com.tpspringcloud.billingservice.feignclients.CustomerFeignClient;
import com.tpspringcloud.billingservice.feignclients.ProductFeignClient;
import com.tpspringcloud.billingservice.models.Customer;
import com.tpspringcloud.billingservice.models.Product;
import com.tpspringcloud.billingservice.repositories.BillRepository;
import com.tpspringcloud.billingservice.repositories.ProductItemRepository;
import org.keycloak.KeycloakSecurityContext;
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
                            ProductFeignClient productFeignClient,
                            KeycloakSecurityContext keycloakSecurityContext){
        return args -> {
            String bearer = "Bearer "+"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJsaF9hWWk3QW1wUldJRUNkR3lPN0ZGV0VJekJiNWFMaUdDWkU0S0swOEM4In0.eyJleHAiOjE2MTA4OTYzMjgsImlhdCI6MTYxMDg5NTcyOCwianRpIjoiMzFjYTY4MzUtMzYxZS00ZTUxLTg4MGItNWZlZTY0MTlmYTBmIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL2Vjb20tbWljcm8tc2VydmljZXMiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNGI4YTkyZmMtOTk2Zi00NTAxLTlkMmQtYjNhMTBlMDAwODc2IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYmlsbGluZy1zZXJ2aWNlIiwic2Vzc2lvbl9zdGF0ZSI6Ijk1OGQwNWI4LWJiOTEtNGM4NC05Njc5LTYwZWZlMDZlZjIwMCIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo4MDgzIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJCSUxMSU5HX01BTkFHRVIiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIiwiVVNFUiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6IkJpbGxpbmcgVXNlciIsInByZWZlcnJlZF91c2VybmFtZSI6ImJpbGxpbmdfdXNlciIsImdpdmVuX25hbWUiOiJCaWxsaW5nIiwiZmFtaWx5X25hbWUiOiJVc2VyIiwiZW1haWwiOiJiaWxsaW5nX3VzZXJAZ21haWwuY29tIn0.QEQuI2it1_CEKRkXY4ylL53v1e8bih6Kor3phkFhgpw92Smvc9Ge2fINV5vUj3vkqVxFMS1eVPsPbnBrPJR17LjfPf4ahBGHKmb0jOBBN2Ci9Dm-T1PG7u7vA9VRfLFh6piJX8faMhCk4Iw89ZyRvm0qu5Xf1tQjwScts-MOYq_Fuhnkwa9NcQxWCoQ2yOTg3dz5mcW1SNIEZlBqvoPxchS-jbsoXuUi9nuXqLvm3WROXvXBcKSpIhgmASpskm8fWOagCI79WYRI1S_cpo-1l35Uq7QpcC12bw4_5fXcGzcDkl2ZIT_O-QaLMTRYE4nyZkTcFyVLq8KWqCOUiLRgyw";
            Customer customer = customerFeignClient.getCustomerById(1L, bearer);
            Bill bill = billRepository.save(new Bill(null, new Date(), null, customer, customer.getId()));
            PagedModel<Product> pageProducts = productFeignClient.pageProducts(bearer);
            System.out.println(pageProducts);
            pageProducts.forEach(product -> {
                System.out.println(product);
                ProductItem productItem = new ProductItem();
                productItem.setPrice(product.getPrice());
                productItem.setQuantity(1+new Random().nextInt(100));
                productItem.setBill(bill);
                productItem.setProductID(product.getId());
                productItem.setProduct(product);
                productItemRepository.save(productItem);
            });
            bill.setProductItems(productItemRepository.findByBillId(bill.getId()));
            System.out.println(customer.toString());
            System.out.println(bill.toString());
        };
    }

}
