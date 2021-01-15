package com.tpspringcloud.billingservice.restcontrollers;

import com.tpspringcloud.billingservice.entities.Bill;
import com.tpspringcloud.billingservice.feignclients.CustomerFeignClient;
import com.tpspringcloud.billingservice.feignclients.ProductFeignClient;
import com.tpspringcloud.billingservice.repositories.BillRepository;
import com.tpspringcloud.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerFeignClient customerFeignClient;
    private ProductFeignClient productFeignClient;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerFeignClient customerFeignClient, ProductFeignClient productFeignClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerFeignClient = customerFeignClient;
        this.productFeignClient = productFeignClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerFeignClient.getCustomerById(bill.getCustomerID()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productFeignClient.getProductById(productItem.getId()));
        });
        return bill;
    }
}
