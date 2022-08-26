package com.jaksim3.bak.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "product", cascade = ALL)
    private List<ProductOrder> productOrder;

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
}
