package com.team4.payroll.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.team4.payroll.dto.CreateClienteDTO;
import com.team4.payroll.dto.ClienteDTO;
import com.team4.payroll.dto.UpdateClienteDTO;
import com.team4.payroll.model.Cliente;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClienteMapper {

    ClienteDTO toDTO(Cliente model);

    List<ClienteDTO> toDTO(List<Cliente> model);
    
    @Mapping(target = "id", ignore = true)
    Cliente toModel(CreateClienteDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Cliente Cliente, UpdateClienteDTO data);
}
