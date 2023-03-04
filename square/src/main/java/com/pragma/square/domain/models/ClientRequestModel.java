package com.pragma.square.domain.models;

public class ClientRequestModel {
    private Long plateId;
    private Integer quantity;

    public ClientRequestModel() {
    }

    public ClientRequestModel(Long plateId, Integer quantity) {
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
