package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.ProductOrder;
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
    private Long productId;
    private LocalDateTime createdDate;

    public static ProductOrderResponseDto of(ProductOrder order) {
        return ProductOrderResponseDto.builder()
                .productId(order.getProductId())
                .createdDate(order.getCreatedDate())
                .build();
    }
}
