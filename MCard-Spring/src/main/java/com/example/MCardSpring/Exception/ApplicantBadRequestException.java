package com.example.MCardSpring.Exception;

/**
 * Gelen istekte applicant entity si ile uyuşmayan veya  eksikler varken atılan hata mesajı
 */
public class ApplicantBadRequestException extends RuntimeException {
    public ApplicantBadRequestException (Long id){
        super("Non valid applicant with id :" + id + "Because includes invalid attribute" );
    }
}
