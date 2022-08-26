package com.jaksim3.bak.domain;

import lombok.*;

import javax.persistence.*;

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
