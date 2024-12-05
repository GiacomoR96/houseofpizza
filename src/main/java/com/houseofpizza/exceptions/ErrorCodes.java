package com.houseofpizza.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    NOT_FOUND("Error: Entity not found."),
    ELEMENTS_TO_ELABORATE_NOT_FOUND("Error: No pizza to produce identified");

    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public static ErrorException generateErrorException(HttpStatus status, Enum<?> errorEnum) {
        return new ErrorException(status, errorEnum);
    }

    /**
     * Generate exception for notFound error - 404 HttpStatus
     * Use for not found path variables in urls/params.
     *
     * @param errorEnum enum
     * @return ErrorException
     */
    public static ErrorException generateError404(Enum<?> errorEnum) {
        return new ErrorException(HttpStatus.NOT_FOUND, errorEnum);
    }

}

