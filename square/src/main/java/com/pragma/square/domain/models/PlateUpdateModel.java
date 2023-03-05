package com.pragma.square.domain.models;

public class PlateUpdateModel {
    private String price;
    private String description;

    public PlateUpdateModel() {
    }

    public PlateUpdateModel(String price, String description) {
        this.price = price;
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

