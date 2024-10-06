package com.skincare;

public class Product {
    private int id;
    private String name;
    private String expirationDate;
    private String category;

    public Product(String name, String expirationDate, String category) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.category = category;
    }

    public Product(int id, String name, String expirationDate, String category) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCategory() {
        return category;
    }
}
