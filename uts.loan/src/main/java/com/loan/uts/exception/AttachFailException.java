package com.loan.uts.exception;

/**
 * Thrown when the file attachment fails.
 */
public class AttachFailException extends Exception {
    public AttachFailException(){
        super("Sorry, error occurs when uploading exception.");
    }

}
