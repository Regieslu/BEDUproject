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

import com.team4.payroll.dto.CreateProveedorDTO;
import com.team4.payroll.dto.ProveedorDTO;
import com.team4.payroll.dto.UpdateProveedorDTO;
import com.team4.payroll.exception.ProveedorNotFoundException;
import com.team4.payroll.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Tag(name = "Endpoints de Proveedor", description = "CRUD de Proveedores")
@RestController
@RequestMapping("proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService service;

    @Operation(summary = "Obtener todos los Proveedors", description = "Obtener la lista que contiene todos los Proveedores")
    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public List<ProveedorDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crear nuevo Proveedor")
    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public ProveedorDTO save(@Valid @RequestBody CreateProveedorDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Obtener Proveedor por ID")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProveedorDTO findById(@PathVariable Long id) throws ProveedorNotFoundException {
        return service.findById(id);  
    }

    @Operation(summary = "Eliminar Proveedor por ID")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ProveedorNotFoundException {
        service.delete(id);
    }

    @Operation(summary = "Actualizar Proveedor por ID")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateProveedorDTO data) throws ProveedorNotFoundException {
        service.update(id, data);
    }

}
