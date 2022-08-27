package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.order.ProductOrder;
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
public class ProductOrderResponseDto {
    private Product product;
    private LocalDateTime createdDate;

    public static ProductOrderResponseDto of(ProductOrder order) {
        return ProductOrderResponseDto.builder()
                .product(order.getProduct())
                .createdDate(order.getCreatedDate())
                .build();
    }
}
