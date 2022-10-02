package DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO implements Serializable {
    private String orderId;
    private String orderCustomerId;
    private String orderTime;
    private String orderDate;
    private int qty;
    private double orderTotal;
    private List<OrderDetailDTO> orderItem;

    public OrderDTO(String orderId, String orderCustomerId, String orderTime, String orderDate, double orderTotal, List<OrderDetailDTO> orderItem) {
        this.orderId = orderId;
        this.orderCustomerId = orderCustomerId;
        this.orderTime = orderTime;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.orderItem = orderItem;
    }

    public OrderDTO(String orderId, String orderCustomerId, String orderTime, String orderDate, List<OrderDetailDTO> orderItem) {
    }

    public OrderDTO(String orderId, String orderCustomerId, String orderTime, String orderDate, int qty, double orderTotal, List<OrderDetailDTO> orderItem) {
        this.orderId = orderId;
        this.orderCustomerId = orderCustomerId;
        this.orderTime = orderTime;
        this.orderDate = orderDate;
        this.qty = qty;
        this.orderTotal = orderTotal;
        this.orderItem = orderItem;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderCustomerId() {
        return orderCustomerId;
    }

    public void setOrderCustomerId(String orderCustomerId) {
        this.orderCustomerId = orderCustomerId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<OrderDetailDTO> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderDetailDTO> orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", orderCustomerId='" + orderCustomerId + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", qty=" + qty +
                ", orderTotal=" + orderTotal +
                ", orderItem=" + orderItem +
                '}';
    }
}
