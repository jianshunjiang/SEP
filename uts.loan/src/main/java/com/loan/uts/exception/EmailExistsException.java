package com.loan.uts.exception;

/**
 * Thrown when email has already be used by another manager.
 */
public class EmailExistsException extends Exception {
    public EmailExistsException(String operation, String email){
        super( operation + " manager failed: email '" + email + "' exists.");
    }
}
