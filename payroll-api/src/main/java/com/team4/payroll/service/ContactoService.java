package com.team4.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.payroll.dto.CreateContactoDTO;
import com.team4.payroll.dto.ContactoDTO;
import com.team4.payroll.dto.UpdateContactoDTO;
import com.team4.payroll.exception.ContactoNotFoundException;
import com.team4.payroll.mapper.ContactoMapper;
import com.team4.payroll.model.Contacto;
import com.team4.payroll.repository.ContactoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoService {
    @Autowired
    private ContactoRepository repository;
    @Autowired
    private ContactoMapper mapper;

    public List<ContactoDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public ContactoDTO save(CreateContactoDTO data) {
        Contacto entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public ContactoDTO findById(Long id) throws ContactoNotFoundException {
        Optional<Contacto> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ContactoNotFoundException(id);
        }
        return mapper.toDTO(result.get());
    }

    public void delete(Long id) throws ContactoNotFoundException {
        if (!repository.existsById(id)) {
            throw new ContactoNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void update(Long id, UpdateContactoDTO data) throws ContactoNotFoundException {
        Optional<Contacto> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ContactoNotFoundException(id);
        }

        Contacto Contacto = result.get();
        mapper.update(Contacto, data);
        repository.save(Contacto);
    }
}
