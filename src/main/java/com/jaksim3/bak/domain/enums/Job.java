package com.jaksim3.bak.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Job {
    STUDENT("학생"),
    SOLDIER("군인"),
    PUBLIC_OFFICIAL("공무원"),
    DEVELOPER("개발자"),
    BUSINESSMAN("사업가"),
    HOUSEWIFE("주부"),
    UNEMPLOYED("무직");

    private final String value;
}
