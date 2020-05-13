package pl.edu.wszib.pracadyplomowa.dto;

public class ProductDetilsDto {

    Long id;
    String name;
    String icon;
    String picture;
    String description;
    double price;
    int availability;
    int amount;


    public ProductDetilsDto() {
    }

    public ProductDetilsDto(Long id, String name, String icon, String picture, String description, double price, int availability, int amount) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.picture = picture;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
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
}
