package com.example.MCardSpring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User da exception durumu için handling
 */
@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String applicantNotFoundHandler (UserNotFoundException userNotFoundException) {
        return userNotFoundException.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String applicantBadRequestHandler (UserBadRequestException userBadRequestException) {
        return userBadRequestException.getMessage();
    }
}
