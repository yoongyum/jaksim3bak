package com.jaksim3.bak.service;

import com.jaksim3.bak.domain.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<List<Product>> findAll();
}
