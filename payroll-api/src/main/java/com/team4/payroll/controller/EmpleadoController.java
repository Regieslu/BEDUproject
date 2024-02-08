package com.team4.payroll.controller;

import com.team4.payroll.dto.CreateEmpleadoDTO;
import com.team4.payroll.dto.EmpleadoDTO;
import com.team4.payroll.dto.UpdateEmpleadoDTO;
import com.team4.payroll.exception.EmpleadoNotFoundException;
import com.team4.payroll.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints de Empleados", description = "CRUD de empleados")
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("empleados")
public class EmpleadoController {


    private final EmpleadoService service;

    @Autowired // This annotation is optional since Spring 4.3 for constructor injection
    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }


    @Operation(summary = "Obtener todos los empleados", description = "Obtener la lista que contiene todos los empleados")
    @GetMapping({"", "/"})
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseStatus(HttpStatus.OK)
    public List<EmpleadoDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crear nuevo empleado")
    @PostMapping({"", "/"})
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

    @Operation(summary = "Eliminar empleado por ID")
    @DeleteMapping("{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws EmpleadoNotFoundException {
        service.deleteById(id);
    }

    @Operation(summary = "Actualizar empleado por ID")
    @PutMapping("{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //public void update(@Param("id") long id, @Valid @RequestBody UpdateEmpleadoDTO dto) throws EmpleadoNotFoundException {
    public void update(@PathVariable("id") long id, @Valid @RequestBody UpdateEmpleadoDTO dto) throws EmpleadoNotFoundException {
        service.update(id, dto);
    }

}
