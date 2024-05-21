package com.houseofpizza.exceptions;

import static com.houseofpizza.exceptions.ErrorCodes.generateErrorException;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ErrorException extends ResponseStatusException {

    public ErrorException(HttpStatus status, String message) {
        super(status, message);
    }

    public static <T> List<T> checkNotEmptyListOrThrowNotFound(List<T> queryResults, ErrorCodes error)
        throws ErrorException {
        return Optional.ofNullable(queryResults)
            .filter(CollectionUtils::isNotEmpty)
            .orElseThrow(() -> generateException(error));
    }

    public static <T> T extractFirstOrThrowNotFound(List<T> queryResults, ErrorCodes error) {
        return Optional.ofNullable(queryResults).flatMap(list -> list.stream().findFirst())
            .orElseThrow(() -> generateException(error));
    }

    public static ErrorException generateException(ErrorCodes error) {
        return generateErrorException(HttpStatus.NOT_FOUND, error);
    }

}
