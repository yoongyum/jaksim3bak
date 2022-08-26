package com.jaksim3.bak.domain;


import com.jaksim3.bak.domain.enums.Authority;
import com.jaksim3.bak.domain.enums.Job;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "MEMBER")
@Entity
public class Member {
    @Id
    @GeneratedValue
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

    @OneToMany(mappedBy = "member")
    private final List<Product> products = new ArrayList<>();

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

    public Long calLoan(int age, int limit) {
        return (long) age * 100_000L + limit;
    }
}

