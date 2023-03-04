package com.pragma.square.domain.models;

import com.pragma.square.infrastructure.output.entity.PlateEntity;

import java.util.Date;
import java.util.List;

public class OrderModel {
    private Long id;
    private Long idClient;
    private Date orderDate;
    private String status;
    private Long idChef;
    private List<PlateModel> plates;
    private RestaurantModel idRestaurant;

    public OrderModel() {
    }

    public OrderModel(Long id, Long idClient, Date orderDate, String status, Long idChef, List<PlateModel> plates, RestaurantModel idRestaurant) {
        this.id = id;
        this.idClient = idClient;
        this.orderDate = orderDate;
        this.status = status;
        this.idChef = idChef;
        this.plates = plates;
        this.idRestaurant = idRestaurant;
    }

    public List<PlateModel> getPlates() {
        return plates;
    }

    public void setPlates(List<PlateModel> plates) {
        this.plates = plates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdChef() {
        return idChef;
    }

    public void setIdChef(Long idChef) {
        this.idChef = idChef;
    }

    public RestaurantModel getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(RestaurantModel idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
