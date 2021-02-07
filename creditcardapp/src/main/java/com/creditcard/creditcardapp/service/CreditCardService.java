package com.creditcard.creditcardapp.service;

import com.creditcard.creditcardapp.entities.CreditCardBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardService extends JpaRepository<CreditCardBean,Long> {

    @Query("select c from CreditCardBean c where c.creditCardNumber=?1")
    List<CreditCardBean>  findByCreditCardNumber(Long cardNumber);
}
