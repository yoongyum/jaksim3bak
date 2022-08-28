package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.conifg.SecurityUtil;
import com.jaksim3.bak.conifg.auth.PrincipalDetails;
import com.jaksim3.bak.service.CartService;
import com.jaksim3.bak.web.dto.CartRequestDto;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cart")
@RestController
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@RequestBody CartRequestDto requestDto) {
        return  ResponseEntity.ok(cartService.save(requestDto));
    }

    @DeleteMapping
    public void delete(@RequestBody CartRequestDto requestDto){
        cartService.delete(requestDto);
    }

    @GetMapping("/cartProducts")
    public ResponseEntity<List<ProductResponseDto>> getCartProducts(){
        return ResponseEntity.ok(cartService.getCartProductList(SecurityUtil.getCurrentMemberId()));
    }
}
