package com.jaksim3.bak.web.dto;

import com.jaksim3.bak.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private Long productId;
        private String institution;
        private String name;
        private Long loan;
        private String logo;
        private int age;
        private String job;

        public static Response of(Product product) {
            return Response.builder()
                    .productId(product.getId())
                    .institution(product.getInstitution())
                    .name(product.getName())
                    .loan(product.getLoan())
                    .logo(product.getLogo())
                    .age(product.getAge())
                    .job(product.getJob())
                    .build();
        }
    }

    @Getter
    public static class Request {
        private String type;
        private String keyword;
    }
}
