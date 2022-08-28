package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.order_product.OrderProduct;
import com.jaksim3.bak.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductRequestDto {
    private String email;
    private Long productId;

    public OrderProduct toOrderProduct(Member member, Product product) {
        return OrderProduct.builder()
                .member(member)
                .product(product)
                .build();
    }
}
