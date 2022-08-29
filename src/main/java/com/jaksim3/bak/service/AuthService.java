package com.jaksim3.bak.service;

import com.jaksim3.bak.conifg.jwt.TokenProvider;
import com.jaksim3.bak.domain.cart.Cart;
import com.jaksim3.bak.domain.cart.CartRepository;
import com.jaksim3.bak.domain.interested.Interested;
import com.jaksim3.bak.domain.interested.InterestedRepository;
import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.member.MemberRepository;
import com.jaksim3.bak.web.dto.MemberRequestDto;
import com.jaksim3.bak.web.dto.MemberResponseDto;
import com.jaksim3.bak.web.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final InterestedRepository interestedRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public MemberResponseDto signup(MemberRequestDto requestDto) {
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
        Member member = requestDto.toMember(passwordEncoder);

        Cart cart = new Cart();
        member.setCart(cart);
        cartRepository.save(cart);

        Interested interested = new Interested();
        member.setInterested(interested);
        interestedRepository.save(interested);

        return MemberResponseDto.of(memberRepository.save(member));
    }

    public TokenDto login(MemberRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.createTokenDto(authentication);
    }
}
