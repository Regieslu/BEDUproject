package com.team4.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.payroll.dto.EmpleadoDTO;
import com.team4.payroll.mapper.EmpleadoMapper;
import com.team4.payroll.repository.EmpleadoRepository;

import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository repository;
    @Autowired
    private EmpleadoMapper mapper;

    public List<EmpleadoDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }
}
