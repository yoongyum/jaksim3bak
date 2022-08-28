package com.jaksim3.bak.service;

import com.jaksim3.bak.domain.cart.Cart;
import com.jaksim3.bak.domain.cart_product.CartProduct;
import com.jaksim3.bak.domain.cart_product.CartProductRepository;
import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.member.MemberRepository;
import com.jaksim3.bak.domain.product.Product;
import com.jaksim3.bak.domain.product.ProductRepository;
import com.jaksim3.bak.web.dto.CartSaveRequestDto;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CartService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;

    @Transactional
    public ProductResponseDto save(CartSaveRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 없습니다"));
        Product product = productRepository.findById(requestDto.getProductId()).orElseThrow(
                () -> new IllegalArgumentException("해당 상품이 없습니다"));

        Cart cart = member.getCart();
        CartProduct cartProduct = CartProduct.builder()
                .cart(cart)
                .product(product)
                .build();
        cart.getCartProductList().forEach(cartPro -> {
            if (cartPro.getProduct().equals(product)) {
                throw new RuntimeException("이미 장바구니에 등록된 상품입니다.");
            }
        });
        cart.addCartProduct(cartProduct);
        product.addCartProduct(cartProduct);
        cartProductRepository.save(cartProduct);

        return ProductResponseDto.of(product);
    }
}
