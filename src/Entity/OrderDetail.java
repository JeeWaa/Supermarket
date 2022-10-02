package Entity;

public class OrderDetail {
    private String code;
    private String orderId;
    private double price;
    private int qty;

    public OrderDetail() {
    }

    public OrderDetail(String code, String orderId, double price, int qty) {
        this.code = code;
        this.orderId = orderId;
        this.price = price;
        this.qty = qty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "code='" + code + '\'' +
                ", orderId='" + orderId + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
