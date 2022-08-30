package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.OrderProductService;
import com.jaksim3.bak.web.dto.OrderDto;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@ApiOperation(value = "금융상품 신청", notes = " ")
@RequestMapping("/orders")
@RestController
public class OrderProductController {
    private final OrderProductService orderProductService;

    @ApiOperation(value = "신청", notes = " ")
    @PostMapping("/{productId}")
    public ResponseEntity<OrderDto.Response> ordering(@PathVariable Long productId){
        return ResponseEntity.ok(orderProductService.ordering(productId));
    }
}
