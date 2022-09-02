package com.jaksim3.bak.service;

import com.jaksim3.bak.web.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto.ProductResponse> findAll();

    List<ProductDto.ProductResponse> findCustom();

    List<ProductDto.ProductResponse> searchKeyword(ProductDto.SearchRequest requestDto);
}
