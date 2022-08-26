package com.jaksim3.bak.service;

import com.jaksim3.bak.domain.Product;
import com.jaksim3.bak.domain.ProductRepository;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductServiceMain implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll()
                        .stream()
                        .map(ProductResponseDto::of).collect(Collectors.toList());
    }
}
