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
    public static class Request {
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
    public static class Response {
        private ProductDto.Response product;
        private LocalDateTime createdDate;

        public static Response of(OrderProduct order) {
            return Response.builder()
                    .product(ProductDto.Response.of(order.getProduct()))
                    .createdDate(order.getCreatedDate())
                    .build();
        }
    }
}
