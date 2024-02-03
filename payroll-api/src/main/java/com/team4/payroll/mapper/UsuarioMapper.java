package com.team4.payroll.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.team4.payroll.dto.CreateUsuarioDTO;
import com.team4.payroll.dto.UsuarioDTO;
import com.team4.payroll.dto.UpdateUsuarioDTO;
import com.team4.payroll.model.Usuario;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario model);

    List<UsuarioDTO> toDTO(List<Usuario> model);
    
    Usuario toModel(CreateUsuarioDTO dto);

    void update(@MappingTarget Usuario Usuario, UpdateUsuarioDTO data);
}
