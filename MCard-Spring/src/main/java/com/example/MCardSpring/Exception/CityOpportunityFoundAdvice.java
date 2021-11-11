package com.example.MCardSpring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Şehir-olanak ta exception durumu için handling
 */
@ControllerAdvice
public class CityOpportunityFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CityOpportunityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cityOpportunityNotFoundHandler (CityOpportunityNotFoundException cityOpportunityNotFoundException) {
        return cityOpportunityNotFoundException.getMessage();
    }
}
