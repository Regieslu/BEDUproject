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

import com.team4.payroll.dto.CreateOrdenDTO;
import com.team4.payroll.dto.OrdenDTO;
import com.team4.payroll.dto.UpdateOrdenDTO;
import com.team4.payroll.exception.OrdenNotFoundException;
import com.team4.payroll.service.OrdenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Tag(name = "Endpoints de Ordens", description = "CRUD de Ordenes")
@RestController
@RequestMapping("ordenes")
public class OrdenController {

    @Autowired
    private OrdenService service;

    @Operation(summary = "Obtener todos los Ordens", description = "Obtener la lista que contiene todos las ordenes")
    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public List<OrdenDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crear nueva Orden")
    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public OrdenDTO save(@Valid @RequestBody CreateOrdenDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Obtener Orden por ID")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrdenDTO findById(@PathVariable Long id) throws OrdenNotFoundException {
        return service.findById(id);  
    }

    @Operation(summary = "Eliminar Orden por ID")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws OrdenNotFoundException {
        service.delete(id);
    }

    @Operation(summary = "Actualizar Orden por ID")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateOrdenDTO data) throws OrdenNotFoundException {
        service.update(id, data);
    }

}
