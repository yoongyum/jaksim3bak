package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.domain.Product;
import com.jaksim3.bak.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products") // Test
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

}
