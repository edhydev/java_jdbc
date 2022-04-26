package com.edhydev.java.jdbc.models;

import java.util.Date;

public class Product {
    private Long id;
    private String name;
    private Double price;
    private Date registerDate;
    private String sku;
    private Category category;

    public Product() {
    }

    public Product(String name, Double price, Date registerDate) {
        this.name = name;
        this.price = price;
        this.registerDate = registerDate;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", registerDate=" + registerDate +
                ", sku='" + sku + '\'' +
                ", category=" + category +
                '}';
    }
}
