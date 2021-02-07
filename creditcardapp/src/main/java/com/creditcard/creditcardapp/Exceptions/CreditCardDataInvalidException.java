package com.creditcard.creditcardapp.Exceptions;

import com.creditcard.creditcardapp.beans.Errors;

/**
 * Exception to handle validations and messages
 */
public class CreditCardDataInvalidException extends Exception {
    private Errors error;

    public CreditCardDataInvalidException(Errors e) {
        this.error = e;
    }

    public Errors getError() {
        return error;
    }

    public void setError(Errors error) {
        this.error = error;
    }
}
