package com.pragma.square.application.mapper;

import com.pragma.square.application.response.OrderResponseDto;
import com.pragma.square.domain.models.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = "spring", //decimo que spring lo tome como un bean y se puede injectar la dependencia
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // ignore errores si no mapea algo
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IOrderResponseMapper {
    OrderResponseDto toResponseDto(OrderModel orderModel);
}
