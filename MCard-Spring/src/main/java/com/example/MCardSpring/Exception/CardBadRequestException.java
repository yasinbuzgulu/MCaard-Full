package com.example.MCardSpring.Exception;

/**
 * Gelen istekte card entity si ile uyuşmayan veya  eksikler varken atılan hata mesajı
 */
public class CardBadRequestException extends RuntimeException{
    public CardBadRequestException (Long id){
        super("Non valid card with id :" + id + "Because includes invalid attribute" );
    }
}
