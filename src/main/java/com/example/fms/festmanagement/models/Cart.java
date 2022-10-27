package com.example.fms.festmanagement.models;

import jakarta.validation.constraints.NotBlank;

public class Cart {

    @NotBlank
    private int cartId;

    private String userId;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
