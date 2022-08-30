package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.MemberService;
import com.jaksim3.bak.web.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@ApiOperation(value = "회원", notes = " ")
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    @ApiOperation(value = "내 정보", notes = " ")
    @GetMapping()
    public ResponseEntity<MemberDto.Response> getMyMemberInfo() {
        MemberDto.Response myInfoBySecurity = memberService.getMyInfoBySecurity();
        return ResponseEntity.ok((myInfoBySecurity));
    }
}