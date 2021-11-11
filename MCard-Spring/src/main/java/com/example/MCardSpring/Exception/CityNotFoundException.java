package com.example.MCardSpring.Exception;

/**
 * City de exception meydana gelirse id nin bulunamadığı mesajı döner
 */
public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(Long id) {
        super( "No city with id: "+ id );
    }
}
