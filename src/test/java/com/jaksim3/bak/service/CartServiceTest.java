package com.jaksim3.bak.service;

import com.jaksim3.bak.domain.cart_product.CartProduct;
import com.jaksim3.bak.domain.cart_product.CartProductRepository;
import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.member.MemberRepository;
import com.jaksim3.bak.domain.product.ProductRepository;
import com.jaksim3.bak.web.dto.CartRequestDto;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
//@SpringBootTest
class CartServiceTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MemberRepository memberRepository;

    String email;
    Long productId;

//    @BeforeEach
    void insertValue(){
        email = "hojun@naver.com";
        productId = 9L;
    }
//    @Test
    void save(){
        ProductResponseDto productDto = cartService.save(CartRequestDto.builder()
                .productId(this.productId)
                .build());
        CartProduct cartProduct = cartProductRepository.findAll().get(0);
        Assertions.assertThat(cartProduct.getProduct().getId()).isEqualTo(productId);
    }
    @Transactional
//    @Test
    void deleteTest(){
        Member member = memberRepository.findByEmail(email).get();
        Long productId = cartService.delete(CartRequestDto.builder()
                .productId(this.productId)
                .build());
        Assertions.assertThat(member.getCart().getCartProductList()).doesNotContain(
                CartProduct.builder()
                        .cart(member.getCart())
                        .product(productRepository.findById(productId).get()).build()
        );
    }
//    @Test
    @Transactional
    void deleteAllTest(){
        cartService.deleteAll();
    }
}