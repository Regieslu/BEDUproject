package com.team4.payroll.service;

import org.springframework.stereotype.Service;

import com.team4.payroll.dto.CreateEmpleadoDTO;
import com.team4.payroll.dto.EmpleadoDTO;
import com.team4.payroll.dto.UpdateEmpleadoDTO;
import com.team4.payroll.exception.EmpleadoNotFoundException;
import com.team4.payroll.mapper.EmpleadoMapper;
import com.team4.payroll.model.Empleado;
import com.team4.payroll.repository.EmpleadoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    private final EmpleadoRepository repository;
    private final EmpleadoMapper mapper;

    public EmpleadoService(EmpleadoRepository repository, EmpleadoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<EmpleadoDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public EmpleadoDTO save(CreateEmpleadoDTO data) {
        Empleado entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public EmpleadoDTO findById(Long id) throws EmpleadoNotFoundException {
        Optional<Empleado> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new EmpleadoNotFoundException(id);
        }
        return mapper.toDTO(result.get());
    }

    public void deleteById(Long id) throws EmpleadoNotFoundException {
        if (!repository.existsById(id)) {
            throw new EmpleadoNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void update(Long id, UpdateEmpleadoDTO data) throws EmpleadoNotFoundException {
        Optional<Empleado> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new EmpleadoNotFoundException(id);
        }

        Empleado empleado = result.get();
        mapper.update(empleado, data);
        repository.save(empleado);
    }


}
