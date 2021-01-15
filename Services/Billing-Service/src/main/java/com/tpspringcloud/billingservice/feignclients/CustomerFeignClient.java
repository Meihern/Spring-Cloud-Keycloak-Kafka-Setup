package com.tpspringcloud.billingservice.feignclients;

import com.tpspringcloud.billingservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerFeignClient {
    @GetMapping(path = "/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id);
}
