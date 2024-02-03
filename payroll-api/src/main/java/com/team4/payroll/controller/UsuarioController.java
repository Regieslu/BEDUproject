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

import com.team4.payroll.dto.CreateUsuarioDTO;
import com.team4.payroll.dto.UsuarioDTO;
import com.team4.payroll.dto.UpdateUsuarioDTO;
import com.team4.payroll.exception.UsuarioNotFoundException;
import com.team4.payroll.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Tag(name = "Endpoints de Usuario", description = "CRUD de Usuario")
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(summary = "Obtener todos los Usuarios", description = "Obtener la lista que contiene todos los usuarios")
    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crear nuevo Usuario")
    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO save(@Valid @RequestBody CreateUsuarioDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Obtener Usuario por ID")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO findById(@PathVariable Long id) throws UsuarioNotFoundException {
        return service.findById(id);  
    }

    @Operation(summary = "Eliminar Usuario por ID")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws UsuarioNotFoundException {
        service.delete(id);
    }

    @Operation(summary = "Actualizar Usuario por ID")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateUsuarioDTO data) throws UsuarioNotFoundException {
        service.update(id, data);
    }

}
