package com.jaksim3.bak.service;

import com.jaksim3.bak.conifg.SecurityUtil;
import com.jaksim3.bak.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jaksim3.bak.web.dto.MemberDto;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 현재 로그인한 회원 정보 꺼내오기
     * @return 회원정보
     * @implNote 토큰 값을 통해서, Member Entity를 가져온다.
     */
    @Transactional(readOnly = true)
    public MemberDto.MemberResponse getMyInfoBySecurity() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberDto.MemberResponse::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }
}
