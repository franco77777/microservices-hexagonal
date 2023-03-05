package com.pragma.square.application.mapper;

import com.pragma.square.application.response.RestaurantPageDto;
import com.pragma.square.application.response.RestaurantResponseDto;
import com.pragma.square.domain.models.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring", //decimo que spring lo tome como un bean y se puede injectar la dependencia
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // ignore errores si no mapea algo
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {
    RestaurantResponseDto toRestaurantResponseDto(RestaurantModel restaurant);
    List<RestaurantResponseDto> toRestaurantResponseDtoList(List<RestaurantModel> restaurants);

    RestaurantPageDto toRestaurantPageDto(RestaurantModel restaurantModel);
}
