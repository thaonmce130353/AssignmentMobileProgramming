package com.demo.object.info;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private float rate;
    private int percentSaleOff;
    private boolean status;
    private int typeId;

    public Product(int id, String name, double price, String description, float rate, int percentSaleOff, boolean status, int typeId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.rate = rate;
        this.percentSaleOff = percentSaleOff;
        this.status = status;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getPercentSaleOff() {
        return percentSaleOff;
    }

    public void setPercentSaleOff(int percentSaleOff) {
        this.percentSaleOff = percentSaleOff;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
