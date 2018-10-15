package com.loan.uts.exception;

/**
 * Thrown when tying to delete a manager that has unhandled work
 */
public class HasUnhandledWorkException extends Exception {
    public HasUnhandledWorkException(Integer id){
        super("Deletion failed: Loan Manager No." + id + " has unhandled work.");
    }
}
