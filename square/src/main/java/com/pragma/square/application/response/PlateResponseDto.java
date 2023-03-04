package com.pragma.square.application.response;

import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateResponseDto {
    private Long id;
    private String name;

    private String price;

    private String description;

    private String imageUrl;

    private CategoryResponseDto idCategory;

    private Boolean active;

    private RestaurantEntity idRestaurant;
    private Integer quantity;
}
