package com.example.fms.festmanagement.models;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class Transaction {

    @NotBlank
    private int transactionId;

    private int amount;

    private Date dateTime;

    private int cartId;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

}
