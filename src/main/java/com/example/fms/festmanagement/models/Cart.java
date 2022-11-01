package com.example.fms.festmanagement.models;

public class Cart {

    private int cartId;

    private String participantEmail;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getParticipantEmail() {
        return this.participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }

}
