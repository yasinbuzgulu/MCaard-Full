package com.example.MCardSpring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Opportunity de exception durumu için handling
 */
@ControllerAdvice
public class OpportunityNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(OpportunityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cityNotFoundHandler (OpportunityNotFoundException opportunityNotFoundException) {
        return opportunityNotFoundException.getMessage();
    }
}
