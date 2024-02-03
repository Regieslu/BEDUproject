package com.team4.payroll.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.team4.payroll.dto.CreateProductoDTO;
import com.team4.payroll.dto.ProductoDTO;
import com.team4.payroll.dto.UpdateProductoDTO;
import com.team4.payroll.model.Producto;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductoMapper {

    ProductoDTO toDTO(Producto model);

    List<ProductoDTO> toDTO(List<Producto> model);
    
    @Mapping(target = "id", ignore = true)
    Producto toModel(CreateProductoDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Producto Producto, UpdateProductoDTO data);
}
