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
public class OrderResponseDto {
    private Long key;
    private LocalDateTime createdDate;

    public static OrderResponseDto of(ProductOrder order) {
        return OrderResponseDto.builder()
                .key(order.getProductId())
                .createdDate(order.getCreatedDate())
                .build();
    }
}
