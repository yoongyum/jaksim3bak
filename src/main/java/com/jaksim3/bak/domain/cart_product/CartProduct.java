package com.jaksim3.bak.domain.cart_product;

import com.jaksim3.bak.domain.basetime.BaseTimeEntity;
import com.jaksim3.bak.domain.cart.Cart;
import com.jaksim3.bak.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "CART_PRODUCT")
@Entity
public class CartProduct extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public CartProduct(Cart cart, Product product){
        this.cart = cart;
        this.product = product;
    }

    public void setCart(Cart cart) {
        if(this.cart != null){
            this.cart.getCartProducts().remove(this);
        }
        this.cart = cart;
        if(!this.cart.getCartProducts().contains(this)){
            this.cart.addCartProduct(this);
        }
    }

    public void setProduct(Product product) {
        if(this.product != null){
            this.product.getCartProducts().remove(this);
        }
        this.product = product;
        if(!this.product.getCartProducts().contains(this)){
            this.product.addCartProduct(this);
        }
    }
}
