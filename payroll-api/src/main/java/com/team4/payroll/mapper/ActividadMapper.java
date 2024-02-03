package com.team4.payroll.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.team4.payroll.dto.CreateActividadDTO;
import com.team4.payroll.dto.ActividadDTO;
import com.team4.payroll.dto.UpdateActividadDTO;
import com.team4.payroll.model.Actividad;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ActividadMapper {

    ActividadDTO toDTO(Actividad model);

    List<ActividadDTO> toDTO(List<Actividad> model);

    Actividad toModel(CreateActividadDTO dto);

    void update(@MappingTarget Actividad Actividad, UpdateActividadDTO data);
}
