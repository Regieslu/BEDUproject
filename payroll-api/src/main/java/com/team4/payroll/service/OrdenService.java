package com.team4.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.payroll.dto.CreateOrdenDTO;
import com.team4.payroll.dto.OrdenDTO;
import com.team4.payroll.dto.UpdateOrdenDTO;
import com.team4.payroll.exception.OrdenNotFoundException;
import com.team4.payroll.mapper.OrdenMapper;
import com.team4.payroll.model.Orden;
import com.team4.payroll.repository.OrdenRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository repository;
    @Autowired
    private OrdenMapper mapper;

    public List<OrdenDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public OrdenDTO save(CreateOrdenDTO data) {
        Orden entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public OrdenDTO findById(Long id) throws OrdenNotFoundException {
        Optional<Orden> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new OrdenNotFoundException(id);
        }
        return mapper.toDTO(result.get());
    }

    public void delete(Long id) throws OrdenNotFoundException {
        if (!repository.existsById(id)) {
            throw new OrdenNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void update(Long id, UpdateOrdenDTO data) throws OrdenNotFoundException {
        Optional<Orden> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new OrdenNotFoundException(id);
        }

        Orden Orden = result.get();
        mapper.update(Orden, data);
        repository.save(Orden);
    }
}
