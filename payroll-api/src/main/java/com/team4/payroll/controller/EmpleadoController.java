package com.team4.payroll.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.team4.payroll.dto.CreateEmpleadoDTO;
import com.team4.payroll.dto.EmpleadoDTO;
import com.team4.payroll.exception.EmpleadoNotFoundException;
import com.team4.payroll.service.EmpleadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Tag(name = "Endpoints de Empleados", description = "CRUD de empleados")
@RestController
@RequestMapping("empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService service;

    @Operation(summary = "Obtener todos los empleados", description = "Obtener la lista que contiene todos los empleados")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmpleadoDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crear nuevo empleado")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpleadoDTO save(@Valid @RequestBody CreateEmpleadoDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Obtener empleado por ID")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmpleadoDTO findById(@PathVariable Long id) throws EmpleadoNotFoundException {
        return service.findById(id);  
    }

}
