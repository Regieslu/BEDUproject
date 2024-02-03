package com.team4.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.payroll.dto.CreateUsuarioDTO;
import com.team4.payroll.dto.UsuarioDTO;
import com.team4.payroll.dto.UpdateUsuarioDTO;
import com.team4.payroll.exception.UsuarioNotFoundException;
import com.team4.payroll.mapper.UsuarioMapper;
import com.team4.payroll.model.Usuario;
import com.team4.payroll.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioMapper mapper;

    public List<UsuarioDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public UsuarioDTO save(CreateUsuarioDTO data) {
        Usuario entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public UsuarioDTO findById(Long id) throws UsuarioNotFoundException {
        Optional<Usuario> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new UsuarioNotFoundException(id);
        }
        return mapper.toDTO(result.get());
    }

    public void delete(Long id) throws UsuarioNotFoundException {
        if (!repository.existsById(id)) {
            throw new UsuarioNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void update(Long id, UpdateUsuarioDTO data) throws UsuarioNotFoundException {
        Optional<Usuario> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new UsuarioNotFoundException(id);
        }

        Usuario Usuario = result.get();
        mapper.update(Usuario, data);
        repository.save(Usuario);
    }
}
