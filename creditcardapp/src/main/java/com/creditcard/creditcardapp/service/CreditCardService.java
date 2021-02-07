package com.creditcard.creditcardapp.service;

import com.creditcard.creditcardapp.entities.CreditCardBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardService extends JpaRepository<CreditCardBean,Long> {

}
