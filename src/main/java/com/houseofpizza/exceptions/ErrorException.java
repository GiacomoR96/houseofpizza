package com.houseofpizza.exceptions;

import static com.houseofpizza.exceptions.ErrorCodes.generateErrorException;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ErrorException extends ResponseStatusException {

    private final Enum<?> errorEnum;

    public ErrorException(HttpStatus status, String message) {
        super(status, message);
        this.errorEnum = null;
    }

    public ErrorException(HttpStatus status, Enum<?> errorEnum) {
        super(status, errorEnum.name());
        this.errorEnum = errorEnum;
    }

    public static <T> List<T> checkNotEmptyListOrThrowNotFound(List<T> queryResults, ErrorCodes error)
        throws ErrorException {
        return Optional.ofNullable(queryResults)
            .filter(CollectionUtils::isNotEmpty)
            .orElseThrow(() -> generateException(error));
    }

    public static ErrorException generateException(ErrorCodes error) {
        return generateErrorException(HttpStatus.NOT_FOUND, error);
    }

}

