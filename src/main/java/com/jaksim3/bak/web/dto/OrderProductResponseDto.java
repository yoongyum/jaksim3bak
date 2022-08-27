package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.order_product.OrderProduct;
import com.jaksim3.bak.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductResponseDto {
    private Product product;
    private LocalDateTime createdDate;

    public static OrderProductResponseDto of(OrderProduct order) {
        return OrderProductResponseDto.builder()
                .product(order.getProduct())
                .createdDate(order.getCreatedDate())
                .build();
    }
}
