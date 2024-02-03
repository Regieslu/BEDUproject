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

import com.team4.payroll.dto.CreateClienteDTO;
import com.team4.payroll.dto.ClienteDTO;
import com.team4.payroll.dto.UpdateClienteDTO;
import com.team4.payroll.exception.ClienteNotFoundException;
import com.team4.payroll.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Tag(name = "Endpoints de Clientes", description = "CRUD de Clientes")
@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(summary = "Obtener todos los Clientes", description = "Obtener la lista que contiene todos los Clientes")
    @GetMapping({ "", "/" })
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crear nuevo Cliente")
    @PostMapping({ "", "/" })
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO save(@Valid @RequestBody CreateClienteDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Obtener Cliente por ID")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO findById(@PathVariable Long id) throws ClienteNotFoundException {
        return service.findById(id);
    }

    @Operation(summary = "Eliminar Cliente por ID")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ClienteNotFoundException {
        service.delete(id);
    }

    @Operation(summary = "Actualizar Cliente por ID")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateClienteDTO data)
            throws ClienteNotFoundException {
        service.update(id, data);
    }

}
