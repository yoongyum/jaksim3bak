package com.jaksim3.bak.service;

import com.jaksim3.bak.conifg.SecurityUtil;
import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.member.MemberRepository;
import com.jaksim3.bak.domain.order_product.OrderProduct;
import com.jaksim3.bak.domain.order_product.OrderProductRepository;
import com.jaksim3.bak.domain.product.Product;
import com.jaksim3.bak.domain.product.ProductRepository;
import com.jaksim3.bak.web.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderDto.Response ordering(Long productId) {
        if (orderProductRepository.existsById(productId)) {
            throw new RuntimeException("이미 신청한 주문입니다.");
        }
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 없습니다"));
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));

        OrderProduct orderProduct = OrderProduct.builder()
                .member(member)
                .product(product)
                .build();
        member.addOrderProduct(orderProduct);
        product.addOrderProduct(orderProduct);
        return OrderDto.Response.of(orderProductRepository.save(orderProduct));
    }
}
