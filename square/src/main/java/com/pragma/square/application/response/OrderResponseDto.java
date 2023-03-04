package com.pragma.square.application.response;

import com.pragma.square.infrastructure.output.entity.PlateEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponseDto {
    private Long id;
    private Long idClient;
    private Date orderDate;
    private String status;
    private Long idChef;
    private RestaurantResponseDto idRestaurant;
    private List<PlateEntity> plates;
}
