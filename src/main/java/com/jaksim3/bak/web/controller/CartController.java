package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.CartService;
import com.jaksim3.bak.web.dto.ProductDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/carts")
@RestController
public class CartController {
    private final CartService cartService;

    @ApiOperation(value = "장바구니 목록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 401, message = "로그인이 필요합니다"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    @GetMapping
    public ResponseEntity<List<ProductDto.ProductResponse>> getCartProducts(){
        return ResponseEntity.ok(cartService.getCartProductList());
    }

    @ApiOperation(value = "장바구니 담기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 401, message = "로그인이 필요합니다"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    @PostMapping("/{productId}")
    public ResponseEntity<ProductDto.ProductResponse> save(@PathVariable("productId") Long productId) {
        return  ResponseEntity.ok(cartService.save(productId));
    }

    @ApiOperation(value = "장바구니 빼기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    @DeleteMapping("/{productId}")
    public void delete(@PathVariable("productId") Long productId){
        cartService.delete(productId);
    }

    @ApiOperation(value = "장바구니 비우기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 401, message = "로그인이 필요합니다"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    @DeleteMapping
    public void deleteAll(){
        cartService.deleteAll();
    }
}
