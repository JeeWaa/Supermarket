package View.Table;

public class ItemTable {
    private String code;
    private String item;
    private String company;
    private String description;
    private double price;
    private int qty;

    public ItemTable() {
    }

    public ItemTable(String code, String item, String company, String description, double price, int qty) {
        this.code = code;
        this.item = item;
        this.company = company;
        this.description = description;
        this.price = price;
        this.qty = qty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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

    @Override
    public String toString() {
        return "ItemTable{" +
                "code='" + code + '\'' +
                ", item='" + item + '\'' +
                ", company='" + company + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
