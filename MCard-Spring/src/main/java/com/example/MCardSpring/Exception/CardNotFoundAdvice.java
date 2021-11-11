package com.example.MCardSpring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Kart ta  exception durumu için handling
 */
@ControllerAdvice
public class CardNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cardNotFoundHandler (CardNotFoundException cardNotFoundException) {
        return cardNotFoundException.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CardBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String cardBadRequestException (CardBadRequestException cardBadRequestException) {
        return cardBadRequestException.getMessage();
    }
}
