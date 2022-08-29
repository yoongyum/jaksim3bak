package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private Long productId;
    private String institution;
    private String name;
    private Long loan;
    private String logo;
    private int age;
    private String job;

    public static ProductResponseDto of(Product product) {
        return ProductResponseDto.builder()
                .productId(product.getId())
                .institution(product.getInstitution())
                .name(product.getName())
                .loan(product.getLoan())
                .logo(product.getLogo())
                .age(product.getAge())
                .job(product.getJob())
                .build();
    }
}
