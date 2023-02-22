package com.pragma.users.domain.model.Square;

public class RestaurantModel {
    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String url;
    private Long userId;
    private String nit;

    public RestaurantModel() {
    }

    public RestaurantModel(Long id, String name, String address, String telephone, String url, Long userId, String nit) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.url = url;
        this.userId = userId;
        this.nit = nit;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
}
