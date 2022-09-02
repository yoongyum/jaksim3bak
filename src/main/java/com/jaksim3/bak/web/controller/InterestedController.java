package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.InterestedService;
import com.jaksim3.bak.web.dto.ProductDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/interests")
@RestController
public class InterestedController {
    private final InterestedService interestedService;

    @ApiOperation(value = "관심상품 목록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 401, message = "로그인이 필요합니다"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    @GetMapping
    public ResponseEntity<List<ProductDto.ProductResponse>> getProductList(){
        return ResponseEntity.ok(interestedService.getProductList());
    }

    @ApiOperation(value = "관심상품 추가")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 401, message = "로그인이 필요합니다"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    @PostMapping("/{productId}")
    public ResponseEntity<ProductDto.ProductResponse> save(@PathVariable Long productId){
        return ResponseEntity.ok(interestedService.save(productId));
    }

    @ApiOperation(value = "관심상품 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    @DeleteMapping("/{productId}")
    public void delete(@PathVariable Long productId){
        interestedService.delete(productId);
    }

    @ApiOperation(value = "관심상품 전체 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 401, message = "로그인이 필요합니다"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    @DeleteMapping
    public void deleteAll(){
        interestedService.deleteAll();
    }
}
