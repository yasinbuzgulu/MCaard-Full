package com.example.MCardSpring.Exception;

/**
 * Opportunity de exception meydana gelirse id nin bulunamadığı mesajı döner
 */
public class OpportunityNotFoundException extends RuntimeException{
    public OpportunityNotFoundException(Long id) {
        super( "No opportunity with id: "+ id );
    }
}
