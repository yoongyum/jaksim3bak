package com.jaksim3.bak.service;

import com.jaksim3.bak.domain.Product;
import com.jaksim3.bak.domain.ProductRepository;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ProductServiceMainTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceMain productServiceMain;

    @BeforeEach
    public void save(){
        productRepository.save(Product.builder()
                .institution("신한은행")
                .name("청년대출")
                .loan(20000000)
                .logo("")
                .age(20)
                .job("백수")
                .build());
    }

    @Test
    void findAll() {
        ResponseEntity<List<ProductResponseDto>> responseEntity = productServiceMain.findAll();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        List<ProductResponseDto> list = productRepository.findAll()
//                        .stream()
//                        .map(ProductResponseDto::of).collect(Collectors.toList());
//
//        ProductResponseDto productDto = list.get(0);
//        Assertions.assertThat(productDto.getInstitution()).isEqualTo("신한은행");
//        Assertions.assertThat(productDto.getName()).isEqualTo("청년대출");


    }
}