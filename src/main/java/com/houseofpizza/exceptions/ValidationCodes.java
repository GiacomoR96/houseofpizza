package com.houseofpizza.exceptions;

import lombok.Getter;

@Getter
public enum ValidationCodes {

    VALIDATION_001("Error: The selected quantity is not valid.");

    private final String message;

    ValidationCodes(String message) {
        this.message = message;
    }

}
