package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.interested.Interested;
import com.jaksim3.bak.domain.interested_product.InterestedProduct;
import com.jaksim3.bak.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class InterestedRequestDto {
    private Long productId;

    public InterestedProduct toInterestedProduct(Interested interested, Product product) {
        return InterestedProduct.builder()
                .interested(interested)
                .product(product)
                .build();
    }
}
