package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.order_product.OrderProduct;
import com.jaksim3.bak.domain.product.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

public class OrderDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderRequest {
        private String email;
        private Long productId;

        public OrderProduct toOrderProduct(Member member, Product product) {
            return OrderProduct.builder()
                    .member(member)
                    .product(product)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderResponse {
        private ProductDto.ProductResponse product;
        private LocalDateTime createdDate;

        public static OrderResponse of(OrderProduct order) {
            return OrderResponse.builder()
                    .product(ProductDto.ProductResponse.of(order.getProduct()))
                    .createdDate(order.getCreatedDate())
                    .build();
        }
    }
}
