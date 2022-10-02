package DTO;

import java.io.Serializable;

public class CustomDTO implements Serializable {
    private String orderId;
    private String customerId;
    private String date;
    private String itemCode;
    private double price;
    private int oty;

    public CustomDTO(String orderId, String customerId, String date, String id, String itemCode, int qty, double price) {
    }

    public CustomDTO(String orderId, String customerId, String date, String itemCode, double price, int oty) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.date = date;
        this.itemCode = itemCode;
        this.price = price;
        this.oty = oty;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOty() {
        return oty;
    }

    public void setOty(int oty) {
        this.oty = oty;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", date='" + date + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", price=" + price +
                ", oty=" + oty +
                '}';
    }
}
