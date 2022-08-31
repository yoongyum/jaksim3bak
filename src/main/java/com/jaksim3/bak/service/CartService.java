package com.jaksim3.bak.service;

import com.jaksim3.bak.conifg.SecurityUtil;
import com.jaksim3.bak.domain.cart.Cart;
import com.jaksim3.bak.domain.cart_product.CartProduct;
import com.jaksim3.bak.domain.cart_product.CartProductRepository;
import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.member.MemberRepository;
import com.jaksim3.bak.domain.product.Product;
import com.jaksim3.bak.domain.product.ProductRepository;
import com.jaksim3.bak.web.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CartService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;

    @Transactional
    public ProductDto.Response save(Long productId) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 없습니다"));
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("해당 상품이 없습니다"));

        Cart cart = member.getCart();
        CartProduct cartProduct = CartProduct.builder()
                        .cart(cart)
                        .product(product)
                        .build();
        cart.getCartProducts().forEach(cartPro -> {
            if (cartPro.getProduct().equals(product)) {
                throw new RuntimeException("이미 장바구니에 등록된 상품입니다.");
            }
        });
        cart.addCartProduct(cartProduct);
        product.addCartProduct(cartProduct);
        cartProductRepository.save(cartProduct);

        return ProductDto.Response.of(product);
    }

    @Transactional
    public Long delete(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("해당 상품이 없습니다"));
        CartProduct cartProduct = cartProductRepository.findByProduct(product).orElseThrow(
                () -> new IllegalArgumentException("해당 장바구니 상품이 없습니다"));

        cartProductRepository.delete(cartProduct);

        return cartProduct.getProduct().getId();
    }

    public List<ProductDto.Response> getCartProductList() {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 없습니다"));

        return cartProductRepository.findAllByCart(member.getCart())
                .stream()
                .map(cartProduct -> ProductDto.Response.of(cartProduct.getProduct()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAll() {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 없습니다."));

        cartProductRepository.deleteAllInBatch((member.getCart().getCartProducts()));
    }
}
