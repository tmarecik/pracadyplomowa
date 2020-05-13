package pl.edu.wszib.pracadyplomowa.dto;

public class BasketProduct {

    Long id;
    String name;
    String icon;
    double price;
    int amount;
    double totalPrice;

    public BasketProduct() {
    }

    public BasketProduct(Long id, String name, String icon, double price, int amount, double totalPrice) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.price = price;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = this.price * this.amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
