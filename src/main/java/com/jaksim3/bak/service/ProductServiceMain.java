package com.jaksim3.bak.service;

import com.jaksim3.bak.domain.product.ProductRepository;
import com.jaksim3.bak.web.dto.ProductDto;
import lombok.RequiredArgsConstructor;
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
    public List<ProductDto.Response> findAll() {
        return productRepository.findAll()
                        .stream()
                        .map(ProductDto.Response::of).collect(Collectors.toList());
    }

}
