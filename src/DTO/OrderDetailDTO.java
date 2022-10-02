package DTO;

import java.io.Serializable;

public class OrderDetailDTO implements Serializable {
    private String orderDetailItemCode;
    private String orderDetailOrderId;
    private double orderDetailPrice;
    private int orderDetailQty;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderDetailItemCode, String orderDetailOrderId, double orderDetailPrice, int orderDetailQty) {
        this.orderDetailItemCode = orderDetailItemCode;
        this.orderDetailOrderId = orderDetailOrderId;
        this.orderDetailPrice = orderDetailPrice;
        this.orderDetailQty = orderDetailQty;
    }

    public String getOrderDetailItemCode() {
        return orderDetailItemCode;
    }

    public void setOrderDetailItemCode(String orderDetailItemCode) {
        this.orderDetailItemCode = orderDetailItemCode;
    }

    public String getOrderDetailOrderId() {
        return orderDetailOrderId;
    }

    public void setOrderDetailOrderId(String orderDetailOrderId) {
        this.orderDetailOrderId = orderDetailOrderId;
    }

    public double getOrderDetailPrice() {
        return orderDetailPrice;
    }

    public void setOrderDetailPrice(double orderDetailPrice) {
        this.orderDetailPrice = orderDetailPrice;
    }

    public int getOrderDetailQty() {
        return orderDetailQty;
    }

    public void setOrderDetailQty(int orderDetailQty) {
        this.orderDetailQty = orderDetailQty;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailItemCode='" + orderDetailItemCode + '\'' +
                ", orderDetailOrderId='" + orderDetailOrderId + '\'' +
                ", orderDetailPrice=" + orderDetailPrice +
                ", orderDetailQty=" + orderDetailQty +
                '}';
    }
}
