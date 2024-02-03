package com.team4.payroll.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.team4.payroll.dto.CreateContactoDTO;
import com.team4.payroll.dto.ContactoDTO;
import com.team4.payroll.dto.UpdateContactoDTO;
import com.team4.payroll.model.Contacto;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContactoMapper {

    ContactoDTO toDTO(Contacto model);

    List<ContactoDTO> toDTO(List<Contacto> model);
    
    @Mapping(target = "id", ignore = true)
    Contacto toModel(CreateContactoDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Contacto Contacto, UpdateContactoDTO data);
}
