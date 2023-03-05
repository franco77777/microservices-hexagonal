package com.pragma.square.infrastructure.output.mapper;

import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.infrastructure.output.entity.PlateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = "spring", //decimo que spring lo tome como un bean y se puede injectar la dependencia
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // ignore errores si no mapea algo
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IPlateEntityMapper {
    PlateEntity toPlateEntity(PlateModel plate1);
    PlateModel toPlateModel(PlateEntity plate2);
}
