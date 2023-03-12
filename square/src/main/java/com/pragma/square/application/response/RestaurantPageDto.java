package com.pragma.square.application.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantPageDto {
   private Long id;
   private String name;
   private String url;

}
