package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {
    private String email;
    private String username;
    private int age;
    private String job;
    private Long availableLoan;
    private List<ProductOrderResponseDto> orderProducts;

    public static MemberResponseDto of(Member member) {
        List<ProductOrderResponseDto> orderProducts = member.getOrderProducts().stream()
                .map(ProductOrderResponseDto::of)
                .collect(Collectors.toList());

        return MemberResponseDto.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .age(member.getAge())
                .job(member.getJob())
                .orderProducts(orderProducts)
                .availableLoan(member.getAvailableLoan())
                .build();
    }
}
