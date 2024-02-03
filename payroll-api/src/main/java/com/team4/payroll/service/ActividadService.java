package com.team4.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.payroll.dto.CreateActividadDTO;
import com.team4.payroll.dto.ActividadDTO;
import com.team4.payroll.dto.UpdateActividadDTO;
import com.team4.payroll.exception.ActividadNotFoundException;
import com.team4.payroll.mapper.ActividadMapper;
import com.team4.payroll.model.Actividad;
import com.team4.payroll.repository.ActividadRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadService {
    @Autowired
    private ActividadRepository repository;
    @Autowired
    private ActividadMapper mapper;

    public List<ActividadDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public ActividadDTO save(CreateActividadDTO data) {
        Actividad entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public ActividadDTO findById(Long id) throws ActividadNotFoundException {
        Optional<Actividad> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ActividadNotFoundException(id);
        }
        return mapper.toDTO(result.get());
    }

    public void delete(Long id) throws ActividadNotFoundException {
        if (!repository.existsById(id)) {
            throw new ActividadNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void update(Long id, UpdateActividadDTO data) throws ActividadNotFoundException {
        Optional<Actividad> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ActividadNotFoundException(id);
        }

        Actividad Actividad = result.get();
        mapper.update(Actividad, data);
        repository.save(Actividad);
    }
}
