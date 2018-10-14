package com.loan.uts.exception;

public class EmailExistsException extends Exception {
    public EmailExistsException(String operation, String email){
        super( operation + " manager failed: email '" + email + "' exists.");
    }
}
