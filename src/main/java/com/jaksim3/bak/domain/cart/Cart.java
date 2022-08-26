package com.jaksim3.bak.domain.cart;

import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.product.Product;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="member_id")
    private Member memberId; // 구매자

    @OneToMany(mappedBy = "cart")
    private List<Product> cartItems = new ArrayList<>();

}
