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

import com.team4.payroll.dto.CreateContactoDTO;
import com.team4.payroll.dto.ContactoDTO;
import com.team4.payroll.dto.UpdateContactoDTO;
import com.team4.payroll.exception.ContactoNotFoundException;
import com.team4.payroll.service.ContactoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Tag(name = "Endpoints de Contactos", description = "CRUD de Contactos")
@RestController
@RequestMapping("contactos")
public class ContactoController {

    @Autowired
    private ContactoService service;

    @Operation(summary = "Obtener todos los Contactos", description = "Obtener la lista que contiene todos los Contactos")
    @GetMapping({ "", "/" })
    @ResponseStatus(HttpStatus.OK)
    public List<ContactoDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crear nuevo Contacto")
    @PostMapping({ "", "/" })
    @ResponseStatus(HttpStatus.CREATED)
    public ContactoDTO save(@Valid @RequestBody CreateContactoDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Obtener Contacto por ID")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactoDTO findById(@PathVariable Long id) throws ContactoNotFoundException {
        return service.findById(id);
    }

    @Operation(summary = "Eliminar Contacto por ID")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ContactoNotFoundException {
        service.delete(id);
    }

    @Operation(summary = "Actualizar Contacto por ID")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateContactoDTO data)
            throws ContactoNotFoundException {
        service.update(id, data);
    }

}
