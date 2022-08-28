package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.CartService;
import com.jaksim3.bak.web.dto.CartRequestDto;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cart")
@RestController
public class CartController {
    private final CartService cartService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getCartProducts(){
        return ResponseEntity.ok(cartService.getCartProductList());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@RequestBody CartRequestDto requestDto) {
        return  ResponseEntity.ok(cartService.save(requestDto));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody CartRequestDto requestDto){
        cartService.delete(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/products")
    public void deleteAll(){
        cartService.deleteAll();
    }
}
