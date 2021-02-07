package com.creditcard.creditcardapp.validator;

import com.creditcard.creditcardapp.beans.Errors;
import com.creditcard.creditcardapp.service.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CreditCardNumberValidatorImplTest {

    @Autowired
    CreditCardNumberValidator validator;

    @Autowired
    Luhn10Impl luhnAlgo;

    @Mock
    CreditCardService service;

    private Errors errors;

    @BeforeEach
    public void setup() {
        errors = new Errors();
    }

    /**
     * test for negative and null and 0
     */
    @Test
    public void isNegativeNumericTest() {
        List<Long> numList = new ArrayList<>(Arrays.asList(null, new Long(-1),0l));
        numList.stream().forEach(num -> validator.isPostiveNumeric(num, errors));
        assertEquals(errors.getErrors().size(), 3);
        errors.getErrors().stream().forEach(e ->
        {
            assertEquals(e.getCode(), ErrorMessage.POSITIVE_NUMBER);
            assertEquals(e.getMessage(), ErrorMessage.POSITIVE_NUMBER_MSG);
        });
    }

    /**
     * check for postive Numbers
     */
    @Test
    public void isPositiveNumericTest() {
        List<Long> numList = new ArrayList<>(Arrays.asList(79927398713l, 12345674l));
        numList.stream().forEach(num -> validator.isPostiveNumeric(num, errors));
        assertEquals(errors.getErrors().size(), 0);
    }

    /**
     * check for all conditions
     */
    @Test
    public void isCreditCardNumberValid() {
        List<Long> numList = new ArrayList<>(Arrays.asList(79927398713l, 12345674l));
        numList.stream().forEach(num -> validator.isCreditCardNumberValid(num, errors));
        assertEquals(errors.getErrors().size(), 0);

    }

    @Test
    public void isCreditCardNumberLengthValidTest() {
        List<Long> numList = new ArrayList<>(Arrays.asList(1234567890123456789l));
        numList.stream().forEach(num -> validator.isCreditCardNumberLengthValid(num, errors));
        assertEquals(errors.getErrors().size(), 0);
    }

    /**
     * check for Invalid credit card numbers
     */
    @Test
    public void isInValidNumberByLuhnTest() {
        List<Long> numList = new ArrayList<>(Arrays.asList(79927398710l, 79927398711l, 79927398712l,
                79927398714l, 79927398715l, 79927398716l, 79927398717l, 79927398718l, 79927398719l));
        numList.stream().forEach(num -> luhnAlgo.isValidNumberByLuhn(num.toString(), errors));
        assertEquals(errors.getErrors().size(), 9);
        errors.getErrors().stream().forEach(e ->
        {
            assertEquals(e.getCode(), ErrorMessage.INVALID_NUMBER);
            assertEquals(e.getMessage(), ErrorMessage.INVALID_NUMBER_MSG);
        });
    }

    @Test
    /**
     * check for valid credit card numbers
     */
    public void isValidNumberByLuhnTest() {
        List<Long> numList = new ArrayList<>(Arrays.asList(79927398713l, 12345674l));
        numList.stream().forEach(num -> luhnAlgo.isValidNumberByLuhn(num.toString(), errors));
        assertEquals(errors.getErrors().size(), 0);
    }

    /**
     * check for Invalid Customer Names
     */
    @Test
    public void isCustomerNameValidTest() {
        List<String> custNames = new ArrayList<>(Arrays.asList(null, "", "a"));
        custNames.stream().forEach(name -> validator.isCustomerNameValid(name, errors));
        assertEquals(errors.getErrors().size(), 3);
        errors.getErrors().stream().forEach(e ->
        {
            assertEquals(e.getCode(), ErrorMessage.INVALID_CUSTOMERNAME);
            assertEquals(e.getMessage(), ErrorMessage.INVALID_CUSTOMERNAME_MSG);
        });
    }

    @Test
    public void doesCardExistsInSystemTest()
    {
        when(service.findByCreditCardNumber(123456l)).thenReturn(new ArrayList<>());
        validator.doesCardExistsInSystem(123456l,errors);
        assertEquals(errors.getErrors().size(), 0);
    }
}
