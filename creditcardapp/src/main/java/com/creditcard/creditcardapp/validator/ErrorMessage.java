package com.creditcard.creditcardapp.validator;

public interface ErrorMessage {
    int CREDITCARD_LENGTH = 20;
    int CUSTOMERNAME_LENGTH = 2;
    String INVALID_LENGTH = "020";
    String INVALID_LENGTH_MSG = "The length of the Credit Card Number is invalid";
    String POSITIVE_NUMBER = "010";
    String POSITIVE_NUMBER_MSG = "Credit Card Number should be Mandatory Positive,greater than 0";
    String INVALID_NUMBER = "030";
    String INVALID_NUMBER_MSG = "The Credit Card Number is invalid";
    String INVALID_CUSTOMERNAME = "040";
    String INVALID_CUSTOMERNAME_MSG = "Customer Name is Mandatory,atleast 2 characters";

}
