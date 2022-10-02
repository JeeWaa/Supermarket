package View.Table;

public class CardTable {
    private String code;
    private String company;
    private String description;
    private double price;
    private int qty;
    private double total;

    public CardTable() {
    }

    public CardTable(String code, String company, String description, double price, int qty, double total) {
        this.code = code;
        this.company = company;
        this.description = description;
        this.price = price;
        this.qty = qty;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CardTable{" +
                "code='" + code + '\'' +
                ", company='" + company + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
