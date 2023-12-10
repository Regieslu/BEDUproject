package com.team4.payroll.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.team4.payroll.dto.CreateEmpleadoDTO;
import com.team4.payroll.dto.EmpleadoDTO;
import com.team4.payroll.dto.UpdateEmpleadoDTO;
import com.team4.payroll.model.Empleado;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmpleadoMapper {

    EmpleadoDTO toDTO(Empleado model);

    List<EmpleadoDTO> toDTO(List<Empleado> model);
    
    @Mapping(target = "id", ignore = true)
    Empleado toModel(CreateEmpleadoDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Empleado empleado, UpdateEmpleadoDTO data);
}
