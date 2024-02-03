package com.team4.payroll.repository;

import com.team4.payroll.model.Producto;
import com.team4.payroll.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Consulta para buscar productos por nombre
    List<Producto> findByNombre(String nombre);

    // Consulta para buscar productos por descripción
    List<Producto> findByDescripcion(String descripcion);

    // Consulta para buscar productos por precio
    List<Producto> findByPrecio(double precio);

    // Consulta para buscar productos por cantidad de stock
    List<Producto> findByCantidadStock(int cantidadStock);

    // Consulta para buscar productos por proveedor
    List<Producto> findByProveedor(Proveedor proveedor);

    // Puedes agregar más consultas según tus necesidades

}
