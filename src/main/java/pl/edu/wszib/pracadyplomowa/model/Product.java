package pl.edu.wszib.pracadyplomowa.model;

import javax.persistence.*;

@Entity
@Table(name = "shop_items")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "name", columnDefinition="VARCHAR(128)")
    String name;

    @Lob
    @Column(name = "picture")
    byte[] picture;

    @Lob
    @Column(name = "icon")
    byte[] icon;

    @Lob
    @Column(name = "description")
    String description;

    @Column(name = "price", precision=6, scale=2)
    double price;

    @Column(name = "availability")
    int availability;

    public Product() {
    }

    public Product(String name, byte[] picture, byte[] icon, String description, double price, int availability) {
        this.name = name;
        this.picture = picture;
        this.icon = icon;
        this.description = description;
        this.price = price;
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

    public byte[] getPicture() {
        return picture;
    }
//
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
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

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
