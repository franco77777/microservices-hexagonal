package com.pragma.square.domain.models;

public class EmployeeModel {
    private Long restaurantId;
    private Long employeeId;

    public EmployeeModel() {
    }

    public EmployeeModel(Long restaurantId, Long employeeId) {
        this.restaurantId = restaurantId;
        this.employeeId = employeeId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
