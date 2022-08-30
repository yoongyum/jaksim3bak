package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.interested_product.InterestedProduct;
import com.jaksim3.bak.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class InterestedDto {
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @Getter
    public static class Request {
        private Long productId;

        public InterestedProduct toInterestedProduct(com.jaksim3.bak.domain.interested.Interested interested, Product product) {
            return InterestedProduct.builder()
                    .interested(interested)
                    .product(product)
                    .build();
        }
    }
}
