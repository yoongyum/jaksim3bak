package com.jaksim3.bak.service;

import com.jaksim3.bak.conifg.SecurityUtil;
import com.jaksim3.bak.domain.interested.Interested;
import com.jaksim3.bak.domain.interested_product.InterestedProduct;
import com.jaksim3.bak.domain.interested_product.InterestedProductRepository;
import com.jaksim3.bak.domain.member.Member;
import com.jaksim3.bak.domain.member.MemberRepository;
import com.jaksim3.bak.domain.product.Product;
import com.jaksim3.bak.domain.product.ProductRepository;
import com.jaksim3.bak.web.dto.ProductDto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InterestedService {
    private final InterestedProductRepository interestedProductRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse save(Long productId) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 없습니다"));

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("해당 상품이 없습니다."));

        Interested interested = member.getInterested();
        InterestedProduct interestedProduct = InterestedProduct.builder()
                .interested(interested)
                .product(product)
                .build();

        interested.getInterestedProducts().forEach(interestedPro -> {
            if (interestedPro.getProduct().equals(product)) {
                throw new RuntimeException("이미 관심에 등록된 상품입니다.");
            }
        });

        interested.addInterestedProduct(interestedProduct);
        product.addInterestedProduct(interestedProduct);
        interestedProductRepository.save(interestedProduct);

        return ProductResponse.of(product);
    }

    public List<ProductResponse> getProductList() {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 없다"));

        return interestedProductRepository.findAllByInterested(member.getInterested())
                .stream()
                .map(interestedProduct -> ProductResponse.of(interestedProduct.getProduct()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long productId) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 없습니다"));

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("해당 상품이 없습니다."));

        InterestedProduct interestedProduct = interestedProductRepository.findByProductAndInterested(product, member.getInterested()).orElseThrow(
                () -> new IllegalArgumentException("해당 관심상품이 없습니다."));
        interestedProductRepository.delete(interestedProduct);
    }

    @Transactional
    public void deleteAll() {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 없다"));

        interestedProductRepository.deleteAllInBatch(member.getInterested().getInterestedProducts());
    }

}
