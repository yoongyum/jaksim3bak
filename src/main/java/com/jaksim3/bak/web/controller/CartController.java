package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.CartService;
import com.jaksim3.bak.web.dto.CartDto;
import com.jaksim3.bak.web.dto.CartDto.Request;
import com.jaksim3.bak.web.dto.ProductDto;
import com.jaksim3.bak.web.dto.ProductDto.Response;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@ApiOperation(value = "장바구니", notes = " ")
@RequestMapping("/cart")
@RestController
public class CartController {
    private final CartService cartService;

    @ApiOperation(value = "장바구니 목록", notes = " ")
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto.Response>> getCartProducts(){
        return ResponseEntity.ok(cartService.getCartProductList());
    }

    @ApiOperation(value = "장바구니 담기", notes = " ")
    @PostMapping
    public ResponseEntity<ProductDto.Response> save(@RequestBody CartDto.Request requestDto) {
        return  ResponseEntity.ok(cartService.save(requestDto));
    }

    @ApiOperation(value = "장바구니 빼기", notes = " ")
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody CartDto.Request requestDto){
        cartService.delete(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "장바구니 비우기", notes = " ")
    @DeleteMapping("/products")
    public void deleteAll(){
        cartService.deleteAll();
    }
}
