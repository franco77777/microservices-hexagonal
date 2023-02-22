package com.pragma.square.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantModel {

    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String url;
    private Long userId;
    private String nit;
}
