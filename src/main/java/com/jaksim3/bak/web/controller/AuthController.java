package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.AuthService;
import com.jaksim3.bak.web.dto.MemberDto;
import com.jaksim3.bak.web.dto.TokenDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @ApiOperation(value = "회원 가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "email 중복 or input값이 올바르지 않음")
    })
    @PostMapping("/signup")
    public ResponseEntity<MemberDto.MemberResponse> signup(@RequestBody MemberDto.MemberRequest requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @ApiOperation(value = "로그인 요청" , notes = "db에 등록된 현재 회원과 일치하면, 토큰 생성 후 리턴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 401, message = "로그인 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberDto.MemberRequest requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }
}
