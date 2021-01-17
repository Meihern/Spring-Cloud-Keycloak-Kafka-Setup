package com.tpspringcloud.billingservice.restcontrollers;

import com.tpspringcloud.billingservice.entities.Bill;
import com.tpspringcloud.billingservice.feignclients.CustomerFeignClient;
import com.tpspringcloud.billingservice.feignclients.ProductFeignClient;
import com.tpspringcloud.billingservice.repositories.BillRepository;
import com.tpspringcloud.billingservice.repositories.ProductItemRepository;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerFeignClient customerFeignClient;
    private ProductFeignClient productFeignClient;
    private KeycloakSecurityContext keycloakSecurityContext;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerFeignClient customerFeignClient, ProductFeignClient productFeignClient, KeycloakSecurityContext keycloakSecurityContext) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerFeignClient = customerFeignClient;
        this.productFeignClient = productFeignClient;
        this.keycloakSecurityContext = keycloakSecurityContext;
    }

    @GetMapping(path = "/fullBill/{id}")
    Bill getBill(@PathVariable(name = "id") Long id){
        String bearer = "Bearer "+keycloakSecurityContext.getTokenString();
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerFeignClient.getCustomerById(bill.getCustomerID(), bearer));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productFeignClient.getProductById(productItem.getId(), bearer));
        });
        return bill;
    }
}
