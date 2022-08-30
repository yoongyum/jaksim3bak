package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.domain.member.MemberRepository;
import com.jaksim3.bak.web.dto.OrderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

//@SpringBootTest
class OrderProductControllerTest {

    @Autowired
    OrderProductController orderProductController;
    @Autowired
    MemberRepository memberRepository;

    OrderDto.Request orderProductRequestDto;

//    @BeforeEach
    void setUp() {
        orderProductRequestDto = OrderDto.Request.builder()
                .email("yoongyum@test.com")
                .productId(2L)
                .build();
    }

//    @Test
    void test(){
        orderProductController.ordering(orderProductRequestDto);
    }

//    @Test
    void deleteMember() {
        memberRepository.deleteAll();
    }
}