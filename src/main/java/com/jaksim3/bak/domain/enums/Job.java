package com.jaksim3.bak.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Job {
    STUDENT("학생", 3_000_000),
    PUBLIC_OFFICIAL("공무원", 100_000_000),
    DEVELOPER("직장인", 50_000_000),
    BUSINESSMAN("자영업", 150_000_000),
    HOUSEWIFE("주부", 26_500_000),
    UNEMPLOYED("무직", 10_000_000);

    private final String label;
    private final int limit;

    public static Job valueOfLabel(String label) {
        return Arrays.stream(values())
                .filter(value -> value.label.equals(label))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("직업이 없습니다."));
    }
}
