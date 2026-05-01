package com.example.nativecrudapp.model;

public class Product {
    private int id;
    private String title;
    private String image;
    private String description;
    private double price;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }
}
