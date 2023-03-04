package com.pragma.square.application.mapper;

import com.pragma.square.application.request.ClientRequest;
import com.pragma.square.domain.models.ClientRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", //decimo que spring lo tome como un bean y se puede injectar la dependencia
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // ignore errores si no mapea algo
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IClientRequestMapper {
    List<ClientRequestModel> toModel (List<ClientRequest> clientRequest);
}
