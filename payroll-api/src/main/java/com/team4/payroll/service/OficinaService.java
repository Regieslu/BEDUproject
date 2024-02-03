package com.team4.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.payroll.dto.CreateOficinaDTO;
import com.team4.payroll.dto.OficinaDTO;
import com.team4.payroll.dto.UpdateOficinaDTO;
import com.team4.payroll.exception.OficinaNotFoundException;
import com.team4.payroll.mapper.OficinaMapper;
import com.team4.payroll.model.Oficina;
import com.team4.payroll.repository.OficinaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OficinaService {
    @Autowired
    private OficinaRepository repository;
    @Autowired
    private OficinaMapper mapper;

    public List<OficinaDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public OficinaDTO save(CreateOficinaDTO data) {
        Oficina entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public OficinaDTO findById(Long id) throws OficinaNotFoundException {
        Optional<Oficina> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new OficinaNotFoundException(id);
        }
        return mapper.toDTO(result.get());
    }

    public void delete(Long id) throws OficinaNotFoundException {
        if (!repository.existsById(id)) {
            throw new OficinaNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void update(Long id, UpdateOficinaDTO data) throws OficinaNotFoundException {
        Optional<Oficina> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new OficinaNotFoundException(id);
        }

        Oficina Oficina = result.get();
        mapper.update(Oficina, data);
        repository.save(Oficina);
    }
}
