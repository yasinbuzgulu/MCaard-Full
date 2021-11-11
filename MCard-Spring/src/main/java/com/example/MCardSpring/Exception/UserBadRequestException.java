package com.example.MCardSpring.Exception;

/**
 * Gelen istekte user entity si ile uyuşmayan veya  eksikler varken atılan hata mesajı
 */
public class UserBadRequestException extends RuntimeException{
    public UserBadRequestException (Long id){
        super("Non valid user with id :" + id + "Because includes invalid attribute" );
    }
}
