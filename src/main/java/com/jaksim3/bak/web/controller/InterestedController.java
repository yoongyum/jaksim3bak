package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.InterestedService;
import com.jaksim3.bak.web.dto.InterestedDto.Request;
import com.jaksim3.bak.web.dto.ProductDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@ApiOperation(value = "관심 상품", notes = " ")
@RequestMapping("/interested")
@RestController
public class InterestedController {
    private final InterestedService interestedService;

    @ApiOperation(value = "관심상품 목록", notes = " ")
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto.Response>> getProductList(){
        return ResponseEntity.ok(interestedService.getProductList());
    }

    @ApiOperation(value = "관심상품 추가", notes = " ")
    @PostMapping
    public ResponseEntity<ProductDto.Response> save(@RequestBody Request requestDto){
        return ResponseEntity.ok(interestedService.save(requestDto));
    }

    @ApiOperation(value = "관심상품 삭제", notes = " ")
    @DeleteMapping
    public void delete(@RequestBody Request requestDto){
        interestedService.delete(requestDto);
    }

    @ApiOperation(value = "관심상품 전체 삭제", notes = " ")
    @DeleteMapping("/products")
    public void deleteAll(){
        interestedService.deleteAll();
    }

}
