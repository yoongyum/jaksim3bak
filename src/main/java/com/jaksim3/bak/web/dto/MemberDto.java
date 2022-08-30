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
    public static class Request {
        private String email;
        private String password;
        private String username;
        private int age;
        private String job;

        public Member toMember(PasswordEncoder passwordEncoder) {
            return com.jaksim3.bak.domain.member.Member.builder()
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
    public static class Response {
        private String email;
        private String username;
        private int age;
        private String job;
        private Long availableLoan;
        private List<OrderDto.Response> orderProducts;

        public static Response of(Member member) {
            List<OrderDto.Response> orderProducts = member.getOrderProducts().stream()
                    .map(OrderDto.Response::of)
                    .collect(Collectors.toList());

            return Response.builder()
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
