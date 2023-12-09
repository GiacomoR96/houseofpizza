package com.houseofpizza.error;

import com.google.common.collect.Iterables;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.houseofpizza.error.ErrorCodes.generateErrorException;

public class ErrorException extends ResponseStatusException {

    public ErrorException(HttpStatus status, String message) {
        super(status, message);
    }

    public static <T> List<T> checkNotEmptyListOrThrowNotFound(List<T> queryResults, ErrorCodes error) throws ErrorException {
        return Optional.ofNullable(queryResults)
                .filter(CollectionUtils::isNotEmpty)
                .orElseThrow(() -> generateErrorException(HttpStatus.NOT_FOUND, error));
    }

    public static <T> T extractFirstOrThrowNotFound(List<T> queryResults, ErrorCodes error) {
        return Optional.ofNullable(queryResults)
                .map((list) -> Iterables.getFirst(list, null))
                .orElseThrow(() -> generateErrorException(HttpStatus.NOT_FOUND, error));
    }

}
