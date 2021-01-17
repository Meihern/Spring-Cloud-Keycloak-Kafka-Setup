package com.tpspringcloud.billingservice.feignclients;

import com.tpspringcloud.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductFeignClient {
    @GetMapping(path = "/products")
    //PagedModel<Product> pageProducts(@RequestParam(value = "page") int page,@RequestParam(value = "size") int size);
    PagedModel<Product> pageProducts(@RequestHeader(name = "Authorization") String bearer);
    @GetMapping(path = "/products/{id}")
    Product getProductById(@PathVariable(name = "id") Long id, @RequestHeader(name = "Authorization") String bearer);
}
