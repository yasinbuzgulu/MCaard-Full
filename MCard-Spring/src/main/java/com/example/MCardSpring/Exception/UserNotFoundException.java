package com.example.MCardSpring.Exception;

/**
 * User ta exception meydana gelirse id nin bulunamadığı mesajı döner
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("No user with id :" + id );
    }

}
