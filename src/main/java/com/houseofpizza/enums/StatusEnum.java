package com.houseofpizza.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    QUEUE("In queue"),
    PROCESSING("Processing"),
    COMPLETED("Completed"),
    DELETED("Deleted");

    private final String code;

}

