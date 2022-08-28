package com.jaksim3.bak.domain.member;


import com.jaksim3.bak.domain.cart.Cart;
import com.jaksim3.bak.domain.basetime.BaseTimeEntity;
import com.jaksim3.bak.domain.enums.Authority;
import com.jaksim3.bak.domain.enums.Job;
import com.jaksim3.bak.domain.interested.Interested;
import com.jaksim3.bak.domain.order_product.OrderProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "MEMBER")
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(STRING)
    private Authority authority;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    @Enumerated(STRING)
    private Job job;

    @Column(nullable = false)
    private Long availableLoan; //대출 한도

    @OneToOne(mappedBy = "member", cascade = ALL)
    private Cart cart;

    @OneToOne(mappedBy = "member", cascade = ALL)
    private Interested interested;

    @OneToMany(mappedBy = "member", cascade = ALL)
    private final List<OrderProduct> orderProducts = new ArrayList<>();

    @Builder
    public Member(String username, String email, String password, Authority authority, int age, Job job) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.age = age;
        this.job = job;
        this.availableLoan = calLoan(age, job.getLimit());
    }

    public String getJob() {
        return job.getLabel();
    }

    private Long calLoan(int age, int limit) {
        return (long) age * 100_000L + limit;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        cart.setMember(this);
    }
    public void setInterested(Interested interested) {
        this.interested = interested;
        interested.setMember(this);
    }

    public Long getAvailableLoan() {
        return Long.sum(availableLoan, -orderProducts.stream()
                .map(orderProduct -> orderProduct.getProduct().getLoan())
                .reduce(0L, Long::sum));
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
    }
}

