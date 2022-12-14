package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.domain.member.MemberRepository;
import com.jaksim3.bak.web.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

//@SpringBootTest
class OrderProductControllerTest {

    @Autowired
    OrderProductController orderProductController;
    @Autowired
    MemberRepository memberRepository;

    OrderDto.OrderRequest orderProductOrderRequestDto;

//    @BeforeEach
    void setUp() {
        orderProductOrderRequestDto = OrderDto.OrderRequest.builder()
                .email("yoongyum@test.com")
                .productId(2L)
                .build();
    }


//    @Test
    void deleteMember() {
        memberRepository.deleteAll();
    }
}