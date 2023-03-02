package com.pragma.square.infrastructure.output.mapper;

import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring", //decimo que spring lo tome como un bean y se puede injectar la dependencia
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // ignore errores si no mapea algo
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IRestaurantEntityMapper {
    RestaurantEntity toRestaurantEntity(RestaurantModel restaurant);
    RestaurantModel toRestaurantModel(RestaurantEntity restaurantEntity);
    List<RestaurantModel> toRestaurantModelList(List<RestaurantEntity> restaurantEntityList);



}
