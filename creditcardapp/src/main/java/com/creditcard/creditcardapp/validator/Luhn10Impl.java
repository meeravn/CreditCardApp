package com.creditcard.creditcardapp.validator;

import com.creditcard.creditcardapp.beans.Error;
import com.creditcard.creditcardapp.beans.Errors;
import org.springframework.stereotype.Component;

@Component
public class Luhn10Impl {
    /*
    checking credit card number based on Luhn Algorithm
     */
    void isValidNumberByLuhn(String ccNumber, Errors errors) {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        if (!(sum % 10 == 0)) {
            errors.getErrors().add(new Error(ErrorMessage.INVALID_NUMBER, ErrorMessage.INVALID_NUMBER_MSG));
        }
    }
}
