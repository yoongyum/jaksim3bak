package com.jaksim3.bak.domain.cart;

import com.jaksim3.bak.domain.cart_product.CartProduct;
import com.jaksim3.bak.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "CART")
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member; // 구매자

    @OneToMany(mappedBy = "cart", cascade = ALL)
    private List<CartProduct> cartProducts = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void addCartProduct(CartProduct cartProduct) {
        this.cartProducts.add(cartProduct);
        if(cartProduct.getCart() != this) {
            cartProduct.setCart(this);
        }
    }

}
