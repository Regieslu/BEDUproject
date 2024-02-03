package com.team4.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.payroll.dto.CreateClienteDTO;
import com.team4.payroll.dto.ClienteDTO;
import com.team4.payroll.dto.UpdateClienteDTO;
import com.team4.payroll.exception.ClienteNotFoundException;
import com.team4.payroll.mapper.ClienteMapper;
import com.team4.payroll.model.Cliente;
import com.team4.payroll.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ClienteMapper mapper;

    public List<ClienteDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public ClienteDTO save(CreateClienteDTO data) {
        Cliente entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public ClienteDTO findById(Long id) throws ClienteNotFoundException {
        Optional<Cliente> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ClienteNotFoundException(id);
        }
        return mapper.toDTO(result.get());
    }

    public void delete(Long id) throws ClienteNotFoundException {
        if (!repository.existsById(id)) {
            throw new ClienteNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void update(Long id, UpdateClienteDTO data) throws ClienteNotFoundException {
        Optional<Cliente> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ClienteNotFoundException(id);
        }

        Cliente Cliente = result.get();
        mapper.update(Cliente, data);
        repository.save(Cliente);
    }
}
