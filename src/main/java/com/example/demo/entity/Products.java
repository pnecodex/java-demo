package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_table")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "product_name")
    private String ProductName;

    public  Products () {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Products(long id, String productName, String description, String price, String quantity) {
        this.id = id;
        ProductName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    private String description;
    private String price;
    private String quantity;

}
