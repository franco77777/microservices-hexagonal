package com.pragma.square.application.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponseDto {
   private Long id;
    private String name;
    private String address;
    private String telephone;
    private String url;
    private Long userId;
    private String nit;
}
