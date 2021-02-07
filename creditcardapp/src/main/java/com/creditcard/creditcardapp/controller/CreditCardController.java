package com.creditcard.creditcardapp.controller;

import com.creditcard.creditcardapp.Exceptions.CreditCardDataInvalidException;
import com.creditcard.creditcardapp.beans.Errors;
import com.creditcard.creditcardapp.entities.CreditCardBean;
import com.creditcard.creditcardapp.service.CreditCardService;
import com.creditcard.creditcardapp.validator.CreditCardNumberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("test/")
public class CreditCardController {
    private static final double DEFAULT_CARD_LIMIT = 500.0;

    @Autowired
    private CreditCardService cardService;

    @Autowired
    CreditCardNumberValidator validator;

    /**
     * @return the list of credit cards in the system
     */
    @GetMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCardBean> getDetails() {
        return this.cardService.findAll();
    }

    /**
     * Saves a Valid Creditcard , and throws exception for an Invalid CreditCard
     *
     * @param bean
     * @throws CreditCardDataInvalidException
     */
    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void saveCreditCard(@Valid @RequestBody CreditCardBean bean) throws CreditCardDataInvalidException {
        Errors errors = new Errors();
        validator.isCustomerNameValid(bean.getCustomerName(), errors);
        validator.isCreditCardNumberValid(bean.getCreditCardNumber(), errors);
        setDefaults(bean);
        if (errors.getErrors().isEmpty()) {

            CreditCardBean creditCard = this.cardService.save(bean);
        } else {
            throw new CreditCardDataInvalidException(errors);
        }
    }

    /**
     * set defaults for fields , like limit is set to 500
     *
     * @param bean
     */
    private void setDefaults(CreditCardBean bean) {
        if (bean.getCardLimit() == 0.0) {
            bean.setCardLimit(DEFAULT_CARD_LIMIT);
        }
    }

}
