package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.cart_product.CartProduct;
import com.jaksim3.bak.domain.interested_product.InterestedProduct;
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
    private String institution;
    private String name;
    private Long loan;
    private String logo;
    private int age;
    private String job;

    @Builder
    public ProductResponseDto(CartProduct cartProduct) {
        this.institution = cartProduct.getProduct().getInstitution();
        this.name = cartProduct.getProduct().getName();
        this.loan = cartProduct.getProduct().getLoan();
        this.logo = cartProduct.getProduct().getLogo();
        this.age = cartProduct.getProduct().getAge();
        this.job = cartProduct.getProduct().getJob();
    }

    @Builder
    public ProductResponseDto(InterestedProduct interestedProduct) {
        this.institution = interestedProduct.getProduct().getInstitution();
        this.name = interestedProduct.getProduct().getName();
        this.loan = interestedProduct.getProduct().getLoan();
        this.logo = interestedProduct.getProduct().getLogo();
        this.age = interestedProduct.getProduct().getAge();
        this.job = interestedProduct.getProduct().getJob();
    }


    public static ProductResponseDto of(Product product) {
        return ProductResponseDto.builder()
                .institution(product.getInstitution())
                .name(product.getName())
                .loan(product.getLoan())
                .logo(product.getLogo())
                .age(product.getAge())
                .job(product.getJob())
                .build();
    }
}
