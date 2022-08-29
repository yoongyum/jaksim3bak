package com.jaksim3.bak.service;

import com.jaksim3.bak.conifg.SecurityUtil;
import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.member.MemberRepository;
import com.jaksim3.bak.domain.product.Product;
import com.jaksim3.bak.domain.product.ProductRepository;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import com.jaksim3.bak.web.dto.SearchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductServiceSearch implements ProductService{
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;


    public List<ProductResponseDto> searchKeyword(SearchRequestDto requestDto) {

        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new RuntimeException("해당 이메일을 가진 회원을 찾을 수 없습니다."));


        List<Product> customization = null;

        if(requestDto.getType().equals("institution")){
            customization = productRepository.findInstitution(member.getAge(), member.getJob(),member.getAvailableLoan(),requestDto.getKeyword());System.out.println("hello "+ customization.size());
        }

        if(requestDto.getType().equals("loan")){
            customization = productRepository.findLoan(member.getAge(), member.getJob(), Long.parseLong(requestDto.getKeyword()));
        }


        assert customization != null;
        return customization.stream().map(ProductResponseDto::of).collect(Collectors.toList());
    }


    @Override
    public List<ProductResponseDto> findAll() {
        return null;
    }
}
