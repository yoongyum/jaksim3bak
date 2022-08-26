package com.jaksim3.bak.service;

import com.jaksim3.bak.domain.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<Product> findAll();
}
