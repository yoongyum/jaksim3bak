package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.OrderProductService;
import com.jaksim3.bak.web.dto.OrderProductRequestDto;
import com.jaksim3.bak.web.dto.OrderProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderProductController {
    private final OrderProductService orderProductService;

    @PostMapping
    public ResponseEntity<OrderProductResponseDto> ordering(OrderProductRequestDto requestDto){
        return ResponseEntity.ok(orderProductService.ordering(requestDto));
    }
}
