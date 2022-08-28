package com.jaksim3.bak.domain.cart_product;

import com.jaksim3.bak.domain.basetime.BaseTimeEntity;
import com.jaksim3.bak.domain.cart.Cart;
import com.jaksim3.bak.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "CART_PRODUCT")
@Entity
public class CartProduct extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public CartProduct(Cart cart, Product product){
        this.cart = cart;
        this.product = product;
    }

    public void setCart(Cart cart) {
        if(this.cart != null){
            this.cart.getCartProductList().remove(this);
        }
        this.cart = cart;
        if(!this.cart.getCartProductList().contains(this)){
            this.cart.addCartProduct(this);
        }
    }

    public void setProduct(Product product) {
        if(this.product != null){
            this.product.getCartProductList().remove(this);
        }
        this.product = product;
        if(!this.product.getCartProductList().contains(this)){
            this.product.addCartProduct(this);
        }
    }
}
