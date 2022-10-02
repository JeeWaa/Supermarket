package Entity;

public class Item {
    private String itemCode;
    private String item;
    private String company;
    private String description;
    private double price;
    private int qty;

    public Item() {
    }

    public Item(String itemCode, String item, String company, String description, double price, int qty) {
        this.itemCode = itemCode;
        this.item = item;
        this.company = company;
        this.description = description;
        this.price = price;
        this.qty = qty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", item='" + item + '\'' +
                ", company='" + company + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
