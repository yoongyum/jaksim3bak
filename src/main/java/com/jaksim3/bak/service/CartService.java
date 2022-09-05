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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;

    /**
     * 카트에 상품 저장
     * @param productId 저장할 상품 고유 id
     * @return 저장된 상품 정보
     */
    @Transactional
    public ProductDto.ProductResponse save(Long productId) {
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

        return ProductDto.ProductResponse.of(product);
    }

    /**
     * 카트에 담긴 특정 상품 삭제
     * @param productId 삭제할 상품 고유 id
     */
    @Transactional
    public void delete(Long productId) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 없습니다"));
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("해당 상품이 없습니다"));
        CartProduct cartProduct = cartProductRepository.findByProductAndCart(product,member.getCart()).orElseThrow(
                () -> new IllegalArgumentException("해당 장바구니 상품이 없습니다"));
        cartProductRepository.delete(cartProduct);
    }

    /**
     * 카트에 담긴 상품 목록 조회
     * @return 카트에 담겨준 상품 목록
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> getCartProductList() {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 없습니다"));

        return cartProductRepository.findAllByCart(member.getCart())
                .stream()
                .map(cartProduct -> ProductDto.ProductResponse.of(cartProduct.getProduct()))
                .collect(Collectors.toList());
    }

    /**
     * 카트에 담긴 상품들 전체삭제
     */
    @Transactional
    public void deleteAll() {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 없습니다."));

        cartProductRepository.deleteAllInBatch((member.getCart().getCartProducts()));
    }
}
