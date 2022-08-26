package com.jaksim3.bak.domain.cart;

import com.jaksim3.bak.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByMemberId(Member member);
}
