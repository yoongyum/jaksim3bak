package com.jaksim3.bak.service;

import com.jaksim3.bak.web.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto.Response> findAll();

    List<ProductDto.Response> findCustom();

    List<ProductDto.Response> searchKeyword(ProductDto.Request requestDto);
}
