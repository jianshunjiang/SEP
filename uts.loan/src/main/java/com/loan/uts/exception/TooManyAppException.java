package com.loan.uts.exception;

/**
 * Thrown when the student's application number achieve the limit.
 */
public class TooManyAppException extends Exception {
    public TooManyAppException(){
        super("Each student can only have 3 applications that have just been submitted, and you have reached the limit.");
    }
}
