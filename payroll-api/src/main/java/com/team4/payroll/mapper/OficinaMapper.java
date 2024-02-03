package com.team4.payroll.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.team4.payroll.dto.CreateOficinaDTO;
import com.team4.payroll.dto.OficinaDTO;
import com.team4.payroll.dto.UpdateOficinaDTO;
import com.team4.payroll.model.Oficina;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OficinaMapper {

    OficinaDTO toDTO(Oficina model);

    List<OficinaDTO> toDTO(List<Oficina> model);
    
    @Mapping(target = "id", ignore = true)
    Oficina toModel(CreateOficinaDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Oficina Oficina, UpdateOficinaDTO data);
}
