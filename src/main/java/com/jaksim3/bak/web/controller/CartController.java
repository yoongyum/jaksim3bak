package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.CartService;
import com.jaksim3.bak.web.dto.CartSaveRequestDto;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cart")
@RestController
public class CartController {
    private final CartService cartService;

    @PostMapping("/save")
    public ResponseEntity<ProductResponseDto> save(@RequestBody CartSaveRequestDto requestDto) {
        return  ResponseEntity.ok(cartService.save(requestDto));
    }
}
