package com.jaksim3.bak.service;

import com.jaksim3.bak.web.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> findAll();
}
