package com.houseofpizza.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LifecycleEnum {
    ACTIVE("Active"),
    DELETED("Deleted");

    private final String code;
}

