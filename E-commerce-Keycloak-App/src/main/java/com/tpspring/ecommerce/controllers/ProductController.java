package com.tpspring.ecommerce.controllers;

import com.tpspring.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/")
    public String index(){
        return "layout_template";
    }

    @GetMapping(path = "/products")
    public String getProducts(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }
}
