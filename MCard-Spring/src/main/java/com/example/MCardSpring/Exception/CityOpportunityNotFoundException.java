package com.example.MCardSpring.Exception;

/**
 *  Şehir-olanak  ta exception meydana gelirse id nin bulunamadığı mesajı döner
 */
public class CityOpportunityNotFoundException extends RuntimeException{
    public CityOpportunityNotFoundException(Long id) {
        super(id + "' sine sahip şehir-olanak kaydı bulunamamıştır!");
    }

}
