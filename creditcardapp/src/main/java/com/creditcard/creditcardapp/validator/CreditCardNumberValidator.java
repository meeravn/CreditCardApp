package com.creditcard.creditcardapp.validator;

import com.creditcard.creditcardapp.beans.Errors;

public interface CreditCardNumberValidator {

    void isCreditCardNumberValid(Long cardNumber, Errors errors);

    void isPostiveNumeric(Long cardNumber, Errors errors);

    void isCreditCardNumberLengthValid(Long cardNumber, Errors errors);

    void isCustomerNameValid(String customerName, Errors errors);

    void doesCardExistsInSystem(Long cardNum, Errors errors);
}
