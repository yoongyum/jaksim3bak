package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.Authority;
import com.jaksim3.bak.domain.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static lombok.AccessLevel.*;

@Getter
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Builder
public class MemberRequestDto {
    @ApiModelProperty(value = "회원가입 이메일", dataType = "String", required = true)
    private String email;

    @ApiModelProperty(value = "비밀번호", dataType = "String", required = true)
    private String password;

    @ApiModelProperty(value = "이름", dataType = "String", required = true)
    private String username;

    @ApiModelProperty(value = "나이", dataType = "int", required = true)
    private int age;

    @ApiModelProperty(value = "직업", dataType = "String", required = true)
    private String job;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .username(username)
                .age(age)
                .job(job)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
