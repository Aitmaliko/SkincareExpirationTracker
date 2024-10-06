package com.skincare;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Product {
    private int id;
    private String name;
    private LocalDate expirationDate;
    private int dDay;

    // Constructor for adding a new product (without id)
    public Product(String name, LocalDate expirationDate) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.dDay = calculateDDay(expirationDate);
    }

    // Constructor for retrieving a product from the database (with id)
    public Product(int id, String name, LocalDate expirationDate, int dDay) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.dDay = dDay;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getDDay() {
        return dDay;
    }

    // Calculate days left (d-day)
    private int calculateDDay(LocalDate expirationDate) {
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", D-Day: " + dDay + " days left";
    }
}
