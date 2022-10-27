package com.example.fms.festmanagement.models;

import jakarta.validation.constraints.NotBlank;

public class CartItemDetails {

    @NotBlank
    private int cidId;

    private int quantity;

    private int cartId;

    private int itemId;

    public int getCidId() {
        return cidId;
    }

    public void setCidId(int cidId) {
        this.cidId = cidId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
