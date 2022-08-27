package com.jaksim3.bak.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class CartSaveRequestDto {
    private String email;
    private Long productId;


}
