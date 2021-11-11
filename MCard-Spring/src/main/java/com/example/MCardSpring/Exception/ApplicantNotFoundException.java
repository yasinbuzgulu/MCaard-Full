package com.example.MCardSpring.Exception;

/**
 * Applicant ta exception meydana gelirse id nin bulunamadığı mesajı döner
 */
 public class ApplicantNotFoundException extends RuntimeException {
     public ApplicantNotFoundException(Long id) {
        super("No applicant with id :" + id );
    }

}
