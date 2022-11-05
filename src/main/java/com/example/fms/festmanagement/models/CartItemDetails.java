package com.example.fms.festmanagement.models;

public class CartItemDetails {

    private int quantity;

    private int cartId;

    private int itemId;

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

    @Override
    public String toString() {
        return "{" +
            " quantity='" + getQuantity() + "'" +
            ", cartId='" + getCartId() + "'" +
            ", itemId='" + getItemId() + "'" +
            "}";
    }
    

}
