package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.OrderProductService;
import com.jaksim3.bak.web.dto.OrderDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderProductController {
    private final OrderProductService orderProductService;

    @ApiOperation(value = "금융상품 신청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 401, message = "이미 처리되었습니다"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    @PostMapping("/{productId}")
    public ResponseEntity<OrderDto.OrderResponse> ordering(@PathVariable Long productId){
        return ResponseEntity.ok(orderProductService.ordering(productId));
    }
}
