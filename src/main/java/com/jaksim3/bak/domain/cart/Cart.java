package com.jaksim3.bak.domain.cart;

import com.jaksim3.bak.domain.cart_product.CartProduct;
import com.jaksim3.bak.domain.member.Member;
import lombok.*;


import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member; // 구매자

    @OneToMany(mappedBy = "cart", cascade = ALL)
    private List<CartProduct> cartProductList = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void addCartProduct(CartProduct cartProduct) {
        this.cartProductList.add(cartProduct);
        if(cartProduct.getCart() != this) {
            cartProduct.setCart(this);
        }
    }

}
