package com.houseofpizza.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    ERROR01("Errore: Elenco vuoto"),
    ERROR02("Errore: Pizza non trovata"),
    ERROR03("Errore: Ordine non presente"),
    STATUS_NOT_FOUND("Error: Status of pizza not found"),
    ERROR04("Errore: Nessuna pizza da produrre individuata");

    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public static ErrorException generateErrorException(HttpStatus status, ErrorCodes errorCodes) {
        return new ErrorException(status, errorCodes.getMessage());
    }

}
