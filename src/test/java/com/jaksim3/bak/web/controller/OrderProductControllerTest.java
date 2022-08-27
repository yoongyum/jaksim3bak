package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.web.dto.OrderProductRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderProductControllerTest {

    @Autowired
    OrderProductController orderProductController;

    OrderProductRequestDto orderProductRequestDto;

    @BeforeEach
    void setUp() {
        orderProductRequestDto = OrderProductRequestDto.builder()
                .email("yoongyum@test.com")
                .productId(2L)
                .build();
    }

    @Test
    void test(){
        orderProductController.ordering(orderProductRequestDto);
    }
}