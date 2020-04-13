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
    @Column(name = "description")
    String description;

    @Column(name = "price", columnDefinition="DOUBLE(5, 2)")
    double price;


    public Product() {
    }

    public Product(String name, byte[] picture, String description, double price) {
//    public Product(String name, String description, double price) {
        this.name = name;
        this.picture = picture;
        this.description = description;
        this.price = price;
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

//    public byte[] getPicture() {
//        return picture;
//    }
//
//    public void setPicture(byte[] picture) {
//        this.picture = picture;
//    }

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
}
