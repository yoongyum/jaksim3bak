package com.jaksim3.bak.service;

import com.jaksim3.bak.conifg.jwt.TokenProvider;
import com.jaksim3.bak.domain.cart.Cart;
import com.jaksim3.bak.domain.interested.Interested;
import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.member.MemberRepository;
import com.jaksim3.bak.web.dto.MemberDto;
import com.jaksim3.bak.web.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public MemberDto.MemberResponse signup(MemberDto.MemberRequest signUpRequestDto) {
        if (memberRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
        Member member = signUpRequestDto.toMember(passwordEncoder);

        Cart cart = Cart.builder().member(member).build();
        member.setCart(cart);

        Interested interested = Interested.builder().member(member).build();
        member.setInterested(interested);

        return MemberDto.MemberResponse.of(memberRepository.save(member));
    }

    public TokenDto login(MemberDto.MemberRequest signUpRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = signUpRequestDto.toAuthentication();
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.createTokenDto(authentication);
    }
}
