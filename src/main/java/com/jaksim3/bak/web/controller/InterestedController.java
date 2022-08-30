package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.InterestedService;
import com.jaksim3.bak.web.dto.ProductDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation(value = "관심 상품", notes = " ")
@RequiredArgsConstructor
@RequestMapping("/interests")
@RestController
public class InterestedController {
    private final InterestedService interestedService;

    @ApiOperation(value = "관심상품 목록", notes = " ")
    @GetMapping
    public ResponseEntity<List<ProductDto.Response>> getProductList(){
        return ResponseEntity.ok(interestedService.getProductList());
    }

    @ApiOperation(value = "관심상품 추가", notes = " ")
    @PostMapping("/{productId}")
    public ResponseEntity<ProductDto.Response> save(@PathVariable Long productId){
        return ResponseEntity.ok(interestedService.save(productId));
    }

    @ApiOperation(value = "관심상품 삭제", notes = " ")
    @DeleteMapping("/{productId}")
    public void delete(@PathVariable Long productId){
        interestedService.delete(productId);
    }

    @ApiOperation(value = "관심상품 전체 삭제", notes = " ")
    @DeleteMapping
    public void deleteAll(){
        interestedService.deleteAll();
    }
}
