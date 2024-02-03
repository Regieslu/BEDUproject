package com.team4.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.payroll.dto.CreateProveedorDTO;
import com.team4.payroll.dto.ProveedorDTO;
import com.team4.payroll.dto.UpdateProveedorDTO;
import com.team4.payroll.exception.ProveedorNotFoundException;
import com.team4.payroll.mapper.ProveedorMapper;
import com.team4.payroll.model.Proveedor;
import com.team4.payroll.repository.ProveedorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository repository;
    @Autowired
    private ProveedorMapper mapper;

    public List<ProveedorDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public ProveedorDTO save(CreateProveedorDTO data) {
        Proveedor entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public ProveedorDTO findById(Long id) throws ProveedorNotFoundException {
        Optional<Proveedor> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ProveedorNotFoundException(id);
        }
        return mapper.toDTO(result.get());
    }

    public void delete(Long id) throws ProveedorNotFoundException {
        if (!repository.existsById(id)) {
            throw new ProveedorNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void update(Long id, UpdateProveedorDTO data) throws ProveedorNotFoundException {
        Optional<Proveedor> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ProveedorNotFoundException(id);
        }

        Proveedor Proveedor = result.get();
        mapper.update(Proveedor, data);
        repository.save(Proveedor);
    }
}
