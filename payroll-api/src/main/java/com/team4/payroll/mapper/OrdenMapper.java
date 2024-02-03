package com.team4.payroll.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.team4.payroll.dto.CreateOrdenDTO;
import com.team4.payroll.dto.OrdenDTO;
import com.team4.payroll.dto.UpdateOrdenDTO;
import com.team4.payroll.model.Orden;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrdenMapper {

    OrdenDTO toDTO(Orden model);

    List<OrdenDTO> toDTO(List<Orden> model);
    
    @Mapping(target = "id", ignore = true)
    Orden toModel(CreateOrdenDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Orden Orden, UpdateOrdenDTO data);
}
