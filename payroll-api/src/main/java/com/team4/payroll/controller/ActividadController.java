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

import com.team4.payroll.dto.CreateActividadDTO;
import com.team4.payroll.dto.ActividadDTO;
import com.team4.payroll.dto.UpdateActividadDTO;
import com.team4.payroll.exception.ActividadNotFoundException;
import com.team4.payroll.service.ActividadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Tag(name = "Endpoints de Actividad", description = "CRUD de Actividad")
@RestController
@RequestMapping("actividades")
public class ActividadController {

    @Autowired
    private ActividadService service;

    @Operation(summary = "Obtener todos los Actividad", description = "Obtener la lista que contiene todos las actividades")
    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public List<ActividadDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crear nueva Actividad")
    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public ActividadDTO save(@Valid @RequestBody CreateActividadDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Obtener Actividad por ID")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActividadDTO findById(@PathVariable Long id) throws ActividadNotFoundException {
        return service.findById(id);  
    }

    @Operation(summary = "Eliminar Actividad por ID")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ActividadNotFoundException {
        service.delete(id);
    }

    @Operation(summary = "Actualizar Actividad por ID")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateActividadDTO data) throws ActividadNotFoundException {
        service.update(id, data);
    }

}
