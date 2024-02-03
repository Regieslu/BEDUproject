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

import com.team4.payroll.dto.CreateOficinaDTO;
import com.team4.payroll.dto.OficinaDTO;
import com.team4.payroll.dto.UpdateOficinaDTO;
import com.team4.payroll.exception.OficinaNotFoundException;
import com.team4.payroll.service.OficinaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Tag(name = "Endpoints de Oficina", description = "CRUD de Oficinas")
@RestController
@RequestMapping("oficinas")
public class OficinaController {

    @Autowired
    private OficinaService service;

    @Operation(summary = "Obtener todos los Oficinas", description = "Obtener la lista que contiene todos las Oficinas")
    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public List<OficinaDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crear nueva Oficina")
    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public OficinaDTO save(@Valid @RequestBody CreateOficinaDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Obtener Oficina por ID")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public OficinaDTO findById(@PathVariable Long id) throws OficinaNotFoundException {
        return service.findById(id);  
    }

    @Operation(summary = "Eliminar Oficina por ID")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws OficinaNotFoundException {
        service.delete(id);
    }

    @Operation(summary = "Actualizar Oficina por ID")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateOficinaDTO data) throws OficinaNotFoundException {
        service.update(id, data);
    }

}
