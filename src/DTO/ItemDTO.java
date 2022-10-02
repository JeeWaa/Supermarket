package DTO;

import java.io.Serializable;

public class ItemDTO implements Serializable {
    private String itemCode;
    private String itemName;
    private String itemCompany;
    private String itemDescription;
    private double itemPrice;
    private int itemQty;

    public ItemDTO() {
    }

    public ItemDTO(String itemCode, String itemName, String itemCompany, String itemDescription, double itemPrice, int itemQty) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemCompany = itemCompany;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCompany() {
        return itemCompany;
    }

    public void setItemCompany(String itemCompany) {
        this.itemCompany = itemCompany;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemCompany='" + itemCompany + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemQty=" + itemQty +
                '}';
    }
}
