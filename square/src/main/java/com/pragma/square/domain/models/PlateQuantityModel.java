package com.pragma.square.domain.models;

public class PlateQuantityModel {
    private Long id;
    private Long plateId;
    private Integer quantity;

    public PlateQuantityModel() {
    }

    public PlateQuantityModel(Long plateId, Integer quantity) {
        this.plateId = plateId;
        this.quantity = quantity;
    }

    public Long getPlateId() {
        return plateId;
    }

    public void setPlateId(Long plateId) {
        this.plateId = plateId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
