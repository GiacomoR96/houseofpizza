package com.houseofpizza.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodes {

    ERROR01("Errore: Elenco vuoto"),
    ERROR02("Errore: Pizza non trovata"),
    ERROR03("Errore: Ordine non presente"),
    ERROR04("Errore: Nessuna pizza da produrre individuata");
    private String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public static ErrorException generateErrorException(HttpStatus status, ErrorCodes errorCodes) {
        return new ErrorException(status, errorCodes.getMessage());
    }

}
