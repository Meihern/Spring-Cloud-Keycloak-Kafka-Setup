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

    /* @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerFeignClient customerFeignClient,
                            ProductFeignClient productFeignClient){
        return args -> {
            String bearer = "Bearer " + "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJsaF9hWWk3QW1wUldJRUNkR3lPN0ZGV0VJekJiNWFMaUdDWkU0S0swOEM4In0.eyJleHAiOjE2MTMwNTk4MDYsImlhdCI6MTYxMzA1OTIwNiwianRpIjoiMDg0ODlhMzMtOTk3Zi00ZDcwLThhOTktZWNjMTZiYTRkOTg2IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL2Vjb20tbWljcm8tc2VydmljZXMiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiOGEzNjFlZmMtNGZiNi00YTQ1LWJiODMtZmFhZGNiMzNiM2RiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiaW52ZW50b3J5LXNlcnZpY2UiLCJzZXNzaW9uX3N0YXRlIjoiMWFkY2ZhN2MtYzllOC00MzU4LTgwNjUtNjA2OGYwODQ4MDdhIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODIiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwiQURNSU4iLCJ1bWFfYXV0aG9yaXphdGlvbiIsIlVTRVIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJBZG1pbiBVc2VyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiYWRtaW5fdXNlciIsImdpdmVuX25hbWUiOiJBZG1pbiIsImZhbWlseV9uYW1lIjoiVXNlciIsImVtYWlsIjoiYWRtaW5fdXNlckBnbWFpbC5jb20ifQ.R9ldOXhkuxJO6DaqSOxHqI7KFiXZvJmuJWvwxzoFdVAZqoBcnyvLZTnyFoscIClQzMvBgPPNwv-y2ZjeePxJqj6p9JzGc44_G705iM4aJqJbnLuYOYS5gjDv0fHJ9TlPQGjKT2rHFpfxNvs2zigUNkidodchc7CxjDa8fBtZeq332ZGT3XFp08NaJyu4hQyeOVLBTpuZ5WK9oTkX_bkXGWcT0lgu6sZhzyA2M3CT6K5WStPrMexie3QOseP16WJ9fwZRtHbliedyWe-RrI87R2fhtOjfvf-PrbPgNe47nY0wPE1UpKk6rgfX_P__iNsU4Uqw_lmAmxdrQkHjc1RJmg";
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
    } */

}
