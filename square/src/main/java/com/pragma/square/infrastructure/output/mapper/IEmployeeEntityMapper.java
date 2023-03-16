package com.pragma.square.infrastructure.output.mapper;

import com.pragma.square.domain.models.EmployeeModel;
import com.pragma.square.infrastructure.output.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", //decimo que spring lo tome como un bean y se puede injectar la dependencia
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // ignore errores si no mapea algo
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeEntityMapper {

    EmployeeEntity toEntity(EmployeeModel employee);
}
