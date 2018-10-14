package com.loan.uts.exception;

public class HasUnhandledWorkException extends Exception {
    public HasUnhandledWorkException(Integer id){
        super("Deletion failed: Loan Manager No." + id + " has unhandled work.");
    }
}
