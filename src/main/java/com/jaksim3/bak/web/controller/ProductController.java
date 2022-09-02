package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.ProductServiceImpl;
import com.jaksim3.bak.web.dto.ProductDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productServiceImpl;


    @ApiOperation(value = "금융상품 전체 목록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    @GetMapping
    public ResponseEntity<List<ProductDto.ProductResponse>> getProductList() {
        return ResponseEntity.ok(productServiceImpl.findAll());
    }

    @ApiOperation(value = "맞춤 금융상품 목록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 401, message = "로그인이 필요합니다"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    @GetMapping("/customization")
    public ResponseEntity<List<ProductDto.ProductResponse>> getCustomizationList() {
        return ResponseEntity.ok(productServiceImpl.findCustom());
    }


    @ApiOperation(value = "금융상품 검색")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 401, message = "로그인이 필요합니다"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    @PostMapping("/search")
    public ResponseEntity<List<ProductDto.ProductResponse>> getSearchList(@RequestBody ProductDto.SearchRequest requestDto) {
        return ResponseEntity.ok(productServiceImpl.searchKeyword(requestDto));
    }


}
