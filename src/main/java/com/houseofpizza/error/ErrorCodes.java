package com.houseofpizza.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    LIST_NOT_FOUND("Error: Empty list"),
    PIZZA_NOT_FOUND("Error: Pizza not found"),
    ORDER_NOT_FOUND("Error: Order not found"),
    STATUS_NOT_FOUND("Error: Status of pizza not found"),
    ELEMENTS_TO_ELABORATE_NOT_FOUND("Error: No pizza to produce identified");

    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public static ErrorException generateErrorException(HttpStatus status, ErrorCodes errorCodes) {
        return new ErrorException(status, errorCodes.getMessage());
    }

}
