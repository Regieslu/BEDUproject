package com.team4.payroll.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.team4.payroll.dto.CreateProveedorDTO;
import com.team4.payroll.dto.ProveedorDTO;
import com.team4.payroll.dto.UpdateProveedorDTO;
import com.team4.payroll.model.Proveedor;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProveedorMapper {

    ProveedorDTO toDTO(Proveedor model);

    List<ProveedorDTO> toDTO(List<Proveedor> model);
    
    @Mapping(target = "id", ignore = true)
    Proveedor toModel(CreateProveedorDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Proveedor Proveedor, UpdateProveedorDTO data);
}
