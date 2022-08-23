package com.jaksim3.bak.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "MEMBER")
@Entity
public class Member {
    @Id
    @GeneratedValue
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
    private String job;

    @Builder
    public Member(String username, String email, String password, Authority authority, int age, String job) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.age = age;
        this.job = job;
    }
}
