package com.houseofpizza.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LifecycleEnum {
    DRAFT("Draft"),
    READY("Ready"),
    DELETED("Deleted");

    private final String code;
}
