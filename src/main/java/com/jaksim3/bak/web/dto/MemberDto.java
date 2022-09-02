package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.enums.Authority;
import com.jaksim3.bak.domain.enums.Job;
import com.jaksim3.bak.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.stream.Collectors;

public class MemberDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberRequest {
        private String email;
        private String password;
        private String username;
        private int age;
        private String job;

        public Member toMember(PasswordEncoder passwordEncoder) {
            return Member.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .username(username)
                    .age(age)
                    .job(Job.valueOfLabel(job))
                    .authority(Authority.ROLE_USER)
                    .build();
        }

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberResponse {
        private String email;
        private String username;
        private int age;
        private String job;
        private Long availableLoan;
        private List<OrderDto.OrderResponse> orderProducts;

        public static MemberResponse of(Member member) {
            List<OrderDto.OrderResponse> orderProducts = member.getOrderProducts().stream()
                    .map(OrderDto.OrderResponse::of)
                    .collect(Collectors.toList());

            return MemberResponse.builder()
                    .email(member.getEmail())
                    .username(member.getUsername())
                    .age(member.getAge())
                    .job(member.getJob())
                    .orderProducts(orderProducts)
                    .availableLoan(member.getAvailableLoan())
                    .build();
        }
    }
}
