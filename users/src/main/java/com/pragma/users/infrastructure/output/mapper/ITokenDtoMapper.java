package com.pragma.users.infrastructure.output.mapper;

import com.pragma.users.domain.model.TokenModel;
import com.pragma.users.infrastructure.output.entity.TokenDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", //decimos que spring lo tome como un bean y se puede injectar la dependencia
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // ignora errores si no mapea algo
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITokenDtoMapper {
    TokenModel toModel(TokenDto token);
}
