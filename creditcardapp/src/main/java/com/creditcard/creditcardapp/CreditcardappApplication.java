package com.creditcard.creditcardapp;

import com.creditcard.creditcardapp.entities.CreditCardBean;
import com.creditcard.creditcardapp.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application to Create a credit card and display all the credit cards
 * in the system
 */
@SpringBootApplication
public class CreditcardappApplication implements CommandLineRunner {

    @Autowired
    private CreditCardService service;

    public static void main(String[] args) {
        SpringApplication.run(CreditcardappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.service.save(new CreditCardBean("Jack", 1234567890l, 0, 5000.0));
        this.service.save(new CreditCardBean("Jones", 1234567890l, 0, 5000.0));
        this.service.save(new CreditCardBean("Jeff", 1234567890l, 0, 5000.0));
    }
}
