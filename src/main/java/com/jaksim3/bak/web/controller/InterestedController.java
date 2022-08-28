package com.jaksim3.bak.web.controller;

import com.jaksim3.bak.service.InterestedService;
import com.jaksim3.bak.web.dto.CartRequestDto;
import com.jaksim3.bak.web.dto.InterestedRequestDto;
import com.jaksim3.bak.web.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/interested")
@RestController
public class InterestedController {
    private final InterestedService interestedService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getProductList(){
        return ResponseEntity.ok(interestedService.getProductList());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@RequestBody InterestedRequestDto requestDto){
        return ResponseEntity.ok(interestedService.save(requestDto));
    }

    @DeleteMapping
    public void delete(@RequestBody InterestedRequestDto requestDto){
        interestedService.delete(requestDto);
    }

    @DeleteMapping("/products")
    public void deleteAll(){
        interestedService.deleteAll();
    }

}
