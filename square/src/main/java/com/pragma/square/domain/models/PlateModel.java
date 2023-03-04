package com.pragma.square.domain.models;

import com.pragma.square.infrastructure.output.entity.RestaurantEntity;

public class PlateModel {
    private Long id;

    private String name;
    private String price;
    private String description;
    private String imageUrl;
    private CategoryModel idCategory;
    private Boolean active;
    private RestaurantEntity idRestaurant;
    private Integer quantity;

    public PlateModel() {
    }

    public PlateModel(Long id, String name, String price, String description, String imageUrl, CategoryModel idCategory, Boolean active, RestaurantEntity idRestaurant, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.idCategory = idCategory;
        this.active = active;
        this.idRestaurant = idRestaurant;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CategoryModel getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(CategoryModel idCategory) {
        this.idCategory = idCategory;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public RestaurantEntity getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(RestaurantEntity idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
