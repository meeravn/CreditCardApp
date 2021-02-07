package com.creditcard.creditcardapp.validator;

import com.creditcard.creditcardapp.beans.Error;
import com.creditcard.creditcardapp.beans.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CreditCardNumberValidatorImpl implements CreditCardNumberValidator {

    @Autowired
    private Luhn10Impl lunhAlgo;

    /**
     * check  all validations for credit card number
     *
     * @param cardNumber
     * @param errors
     */
    @Override
    public void isCreditCardNumberValid(Long cardNumber, Errors errors) {
        isPostiveNumeric(cardNumber, errors);
        if (errors.getErrors().isEmpty()) {
            isCreditCardNumberLengthValid(cardNumber, errors);
            lunhAlgo.isValidNumberByLuhn(String.valueOf(cardNumber), errors);
        }
    }

    /**
     * check for a positive long number
     *
     * @param cardNumber
     * @param errors
     */
    @Override
    public void isPostiveNumeric(Long cardNumber, Errors errors) {
        if (cardNumber == null || cardNumber < 0) {
            errors.getErrors().add(new Error(ErrorMessage.POSITIVE_NUMBER, ErrorMessage.POSITIVE_NUMBER_MSG));
        }
    }

    /**
     * check credit card number length is Valid
     *
     * @param cardNumber
     * @param errors
     */
    @Override
    public void isCreditCardNumberLengthValid(Long cardNumber, Errors errors) {
        if (cardNumber.toString().length() >= ErrorMessage.CREDITCARD_LENGTH) {
            errors.getErrors().add(new Error(ErrorMessage.INVALID_LENGTH, ErrorMessage.INVALID_LENGTH_MSG));
        }
    }

    /**
     * @param customerName
     * @param errors
     */
    @Override
    public void isCustomerNameValid(String customerName, Errors errors) {
        if (!StringUtils.hasLength(customerName) || customerName.length() < ErrorMessage.CUSTOMERNAME_LENGTH) {
            errors.getErrors().add(new Error(ErrorMessage.INVALID_CUSTOMERNAME, ErrorMessage.INVALID_CUSTOMERNAME_MSG));
        }
    }
}
