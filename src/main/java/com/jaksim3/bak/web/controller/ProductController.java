package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.ProductServiceMain;
import com.jaksim3.bak.service.ProductServiceCustomization;
import com.jaksim3.bak.service.ProductServiceSearch;
import com.jaksim3.bak.web.dto.ProductDto;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiOperation(value = "금융상품", notes = " ")
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceMain productServiceMain;
    private final ProductServiceCustomization productServicePush;
    private final ProductServiceSearch productServiceSearch;

    @ApiOperation(value = "금융상품 전체 목록", notes = "비 로그인시 모든 상품을 보여 줍니다.")
    // 전체 상품 목록
    @GetMapping
    public ResponseEntity<List<ProductDto.Response>> getProductList() {
        return ResponseEntity.ok(productServiceMain.findAll());
    }

    @ApiOperation(value = "맞춤 금융상품 목록", notes = "로그인시 회원의 나이, 직업에 맞는 금융상품을 보여 줍니다.")
    @GetMapping("/customization")
    public ResponseEntity<List<ProductDto.Response>> getCustomizationList() {
        return ResponseEntity.ok(productServicePush.findAll());
    }

    @ApiOperation(value = "금융상품 검색", notes = "기관명과 대출금을 키워드로 상품을 검색합니다.")
    @PostMapping("/search")
    public ResponseEntity<List<ProductDto.Response>> getSearchList(@RequestBody ProductDto.Request requestDto) {
        return ResponseEntity.ok(productServiceSearch.searchKeyword(requestDto));
    }
}
