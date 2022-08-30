package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.cart_product.CartProduct;
import com.jaksim3.bak.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CartDto {
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @Getter
    public static class Request {
        private Long productId;

        public CartProduct toEntity(com.jaksim3.bak.domain.cart.Cart cart, Product product) {
            return CartProduct.builder()
                    .cart(cart)
                    .product(product)
                    .build();
        }
    }
}
