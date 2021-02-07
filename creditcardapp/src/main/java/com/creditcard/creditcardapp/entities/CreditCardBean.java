package com.creditcard.creditcardapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Credit card bean - has customer name , card number , balance and limit
 */
@Entity
@Table(name = "creditcard")
public class CreditCardBean implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Please provide name")
    @NotNull(message = "Please provide name")
    private String customerName;

    private Long creditCardNumber;

    private double balance;

    private double cardLimit;

    public CreditCardBean() {
    }

    public CreditCardBean(String customerName, Long creditCardNumber, double balance, double cardLimit) {
        this.customerName = customerName;
        this.creditCardNumber = creditCardNumber;
        this.balance = balance;
        this.cardLimit = cardLimit;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public double getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(double cardLimit) {
        this.cardLimit = cardLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardBean that = (CreditCardBean) o;
        return id == that.id &&
                Double.compare(that.getBalance(), getBalance()) == 0 &&
                Double.compare(that.getCardLimit(), getCardLimit()) == 0 &&
                getCustomerName().equals(that.getCustomerName()) &&
                getCreditCardNumber().equals(that.getCreditCardNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getCustomerName(), getCreditCardNumber(), getBalance(), getCardLimit());
    }
}