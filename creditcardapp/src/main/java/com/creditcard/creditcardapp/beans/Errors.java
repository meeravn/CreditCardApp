package com.creditcard.creditcardapp.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper object to Handle all the errors in a list
 */
public class Errors {

    private List<Error> errors;

    public Errors() {
        this.errors = new ArrayList<>();
    }

    public List<Error> getErrors() {
        return this.errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
