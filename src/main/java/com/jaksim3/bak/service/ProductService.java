package com.jaksim3.bak.service;

import com.jaksim3.bak.domain.Product;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> findAll();
}
