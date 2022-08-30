package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.CartService;
import com.jaksim3.bak.web.dto.ProductDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@ApiOperation(value = "장바구니", notes = " ")
@RequestMapping("/carts")
@RestController
public class CartController {
    private final CartService cartService;

    @ApiOperation(value = "장바구니 목록", notes = " ")
    @GetMapping
    public ResponseEntity<List<ProductDto.Response>> getCartProducts(){
        return ResponseEntity.ok(cartService.getCartProductList());
    }

    @ApiOperation(value = "장바구니 담기", notes = " ")
    @PostMapping("/{productId}")
    public ResponseEntity<ProductDto.Response> save(@PathVariable("productId") Long productId) {
        return  ResponseEntity.ok(cartService.save(productId));
    }

    @ApiOperation(value = "장바구니 빼기", notes = " ")
    @DeleteMapping("/{productId}")
    public void delete(@PathVariable("productId") Long productId){
        cartService.delete(productId);
    }

    @ApiOperation(value = "장바구니 비우기", notes = " ")
    @DeleteMapping
    public void deleteAll(){
        cartService.deleteAll();
    }
}
