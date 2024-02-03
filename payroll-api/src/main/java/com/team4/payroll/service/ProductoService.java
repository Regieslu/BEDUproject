package com.team4.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.payroll.dto.CreateProductoDTO;
import com.team4.payroll.dto.ProductoDTO;
import com.team4.payroll.dto.UpdateProductoDTO;
import com.team4.payroll.exception.ProductoNotFoundException;
import com.team4.payroll.mapper.ProductoMapper;
import com.team4.payroll.model.Producto;
import com.team4.payroll.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository repository;
    @Autowired
    private ProductoMapper mapper;

    public List<ProductoDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public ProductoDTO save(CreateProductoDTO data) {
        Producto entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public ProductoDTO findById(Long id) throws ProductoNotFoundException {
        Optional<Producto> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ProductoNotFoundException(id);
        }
        return mapper.toDTO(result.get());
    }

    public void delete(Long id) throws ProductoNotFoundException {
        if (!repository.existsById(id)) {
            throw new ProductoNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void update(Long id, UpdateProductoDTO data) throws ProductoNotFoundException {
        Optional<Producto> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ProductoNotFoundException(id);
        }

        Producto Producto = result.get();
        mapper.update(Producto, data);
        repository.save(Producto);
    }
}
