package com.jaksim3.bak.service;

import com.jaksim3.bak.conifg.SecurityUtil;
import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.member.MemberRepository;
import com.jaksim3.bak.domain.product.ProductRepository;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductServicePush implements ProductService{
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<ProductResponseDto> findAll() {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new RuntimeException("해당 이메일을 가진 회원을 찾을 수 없습니다."));

        return productRepository.findByAgeAndJob(member.getAge(), member.getJob())
                .stream()
                .map(ProductResponseDto::of).collect(Collectors.toList());
    }
}
