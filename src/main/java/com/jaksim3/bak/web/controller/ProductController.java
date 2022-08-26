package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.ProductServiceMain;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceMain productServiceMain;

    @GetMapping("/productList") // Test
    public ResponseEntity<List<ProductResponseDto>> getProductList() {
        return productServiceMain.findAll();
    }

}
