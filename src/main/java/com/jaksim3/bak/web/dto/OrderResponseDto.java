package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.Order;
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

    public static OrderResponseDto of(Order order) {
        return OrderResponseDto.builder()
                .key(order.getKey())
                .createdDate(order.getCreatedDate())
                .build();
    }
}
