package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.cart.Cart;
import com.jaksim3.bak.domain.cart_product.CartProduct;
import com.jaksim3.bak.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class CartRequestDto {
    private String email;
    private Long productId;

    public CartProduct toEntity(Cart cart, Product product){
        return CartProduct.builder()
                .cart(cart)
                .product(product)
                .build();
    }
}
