package com.example.MCardSpring.Exception;

/**
 *  Kart ta exception meydana gelirse id nin bulunamadığı mesajı döner
 */
public class CardNotFoundException extends  RuntimeException{
    public CardNotFoundException(Long id) {
        super(id + "' sine sahip kart bulunamamıştır!");
    }

}
