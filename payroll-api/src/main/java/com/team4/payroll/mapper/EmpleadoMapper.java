package com.team4.payroll.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.team4.payroll.dto.CreateEmpleadoDTO;
import com.team4.payroll.dto.EmpleadoDTO;
import com.team4.payroll.model.Empleado;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmpleadoMapper {

    EmpleadoDTO toDTO(Empleado model);

    List<EmpleadoDTO> toDTO(List<Empleado> model);

    @Mapping(target = "idEmpleado", ignore = true)
    Empleado toModel(CreateEmpleadoDTO data);
}
