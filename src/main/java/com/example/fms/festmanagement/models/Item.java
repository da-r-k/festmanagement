package com.example.fms.festmanagement.models;

public class Item {

    private int itemId;

    private String itemName;

    private int sellingPrice;

    private int costPrice;

    private int stock;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;

    }
    
    @Override
    public String toString() {
        return "{" +
            " itemId='" + getItemId() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", sellingPrice='" + getSellingPrice() + "'" +
            ", costPrice='" + getCostPrice() + "'" +
            ", stock='" + getStock() + "'" +
            "}";
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
