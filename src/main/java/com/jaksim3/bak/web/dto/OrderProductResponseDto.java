package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.order_product.OrderProduct;
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
    private ProductResponseDto product;
    private LocalDateTime createdDate;

    public static OrderProductResponseDto of(OrderProduct order) {
        return OrderProductResponseDto.builder()
                .product(ProductResponseDto.of(order.getProduct()))
                .createdDate(order.getCreatedDate())
                .build();
    }
}
