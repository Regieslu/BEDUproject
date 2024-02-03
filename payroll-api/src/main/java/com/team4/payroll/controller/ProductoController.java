package com.team4.payroll.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.team4.payroll.dto.CreateProductoDTO;
import com.team4.payroll.dto.ProductoDTO;
import com.team4.payroll.dto.UpdateProductoDTO;
import com.team4.payroll.exception.ProductoNotFoundException;
import com.team4.payroll.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Tag(name = "Endpoints de Producto", description = "CRUD de Productos")
@RestController
@RequestMapping("productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @Operation(summary = "Obtener todos los Productos", description = "Obtener la lista que contiene todos los Productos")
    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crear nuevo Producto")
    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoDTO save(@Valid @RequestBody CreateProductoDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Obtener Producto por ID")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoDTO findById(@PathVariable Long id) throws ProductoNotFoundException {
        return service.findById(id);  
    }

    @Operation(summary = "Eliminar Producto por ID")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ProductoNotFoundException {
        service.delete(id);
    }

    @Operation(summary = "Actualizar Producto por ID")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateProductoDTO data) throws ProductoNotFoundException {
        service.update(id, data);
    }

}
