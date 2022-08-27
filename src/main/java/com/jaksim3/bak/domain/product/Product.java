package com.jaksim3.bak.domain.product;

import com.jaksim3.bak.domain.order_product.OrderProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.jaksim3.bak.domain.cart_product.CartProduct;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<CartProduct> cartProductList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(length = 50, nullable = false)
    private String institution;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private Long loan;

    private String logo;

    private int age;

    private String job;

    @Builder
    public Product (String institution, String name, long loan, String logo, int age, String job){
        this.institution = institution;
        this.name = name;
        this.loan = loan;
        this.logo = logo;
        this.age = age;
        this.job = job;
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
    }

    public void addCartProduct(CartProduct cartProduct) {
        this.cartProductList.add(cartProduct);
        if(cartProduct.getProduct() != this) {
            cartProduct.setProduct(this);
        }
    }
}
