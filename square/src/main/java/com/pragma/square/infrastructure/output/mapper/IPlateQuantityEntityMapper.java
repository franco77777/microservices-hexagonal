package com.pragma.square.infrastructure.output.mapper;

import com.pragma.square.domain.models.PlateQuantityModel;
import com.pragma.square.infrastructure.output.entity.PlateQuantityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", //decimo que spring lo tome como un bean y se puede injectar la dependencia
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // ignore errores si no mapea algo
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateQuantityEntityMapper {
    PlateQuantityEntity toEntity(PlateQuantityModel plateQuantity);
    PlateQuantityModel toModel(PlateQuantityEntity plateQuantityEntity);
}
