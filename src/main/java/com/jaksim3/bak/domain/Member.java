package com.jaksim3.bak.domain;


import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
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

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String job;
}
