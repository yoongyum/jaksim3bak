package com.jaksim3.bak.service;

import com.jaksim3.bak.domain.cart.Cart;
import com.jaksim3.bak.domain.cart.CartRepository;
import com.jaksim3.bak.domain.cart_product.CartProduct;
import com.jaksim3.bak.domain.cart_product.CartProductRepository;
import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.member.MemberRepository;
import com.jaksim3.bak.domain.product.ProductRepository;
import com.jaksim3.bak.web.dto.CartSaveRequestDto;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CartServiceTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private MemberRepository memberRepository;

    String email;
    Long productId;

    @BeforeEach
    void insertValue(){
        email = "hojun@naver.com";
        productId = 9L;
    }
    @Test
    void save(){
        ProductResponseDto productDto = cartService.save(CartSaveRequestDto.builder()
                .email(this.email)
                .productId(this.productId)
                .build());
        CartProduct cartProduct = cartProductRepository.findAll().get(0);
        Assertions.assertThat(cartProduct.getProduct().getId()).isEqualTo(productId);
    }
//    @Test
//    void deleteTest(){
//        Member member = memberRepository.findByEmail(email).get();
//        memberRepository.delete(member);
//
//    }
}