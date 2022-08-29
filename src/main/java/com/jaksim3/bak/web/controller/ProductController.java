package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.ProductServiceMain;
import com.jaksim3.bak.service.ProductServiceCustomization;
import com.jaksim3.bak.service.ProductServiceSearch;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import com.jaksim3.bak.web.dto.SearchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceMain productServiceMain;
    private final ProductServiceCustomization productServicePush;
    private final ProductServiceSearch productServiceSearch;

    // 전체 상품 목록
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProductList() {
        return ResponseEntity.ok(productServiceMain.findAll());
    }

    // 맞춤 상품 푸시
    @GetMapping("/customization")
    public ResponseEntity<List<ProductResponseDto>> getCustomizationList() {
        return ResponseEntity.ok(productServicePush.findAll());
    }

    //상품 검색
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> getSearchList(@RequestBody SearchRequestDto requestDto) {
        return ResponseEntity.ok(productServiceSearch.searchKeyword(requestDto));
    }
}
