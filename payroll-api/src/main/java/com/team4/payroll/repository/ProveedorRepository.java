package com.team4.payroll.repository;

import com.team4.payroll.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    // Consulta para buscar un proveedor por su nombre
    Proveedor findByNombre(String nombre);

    // Consulta para buscar proveedores por dirección
    List<Proveedor> findByDireccion(String direccion);

    // Consulta para buscar proveedores por teléfono
    List<Proveedor> findByTelefono(String telefono);

    // Consulta para buscar un proveedor por su correo electrónico
    Proveedor findByCorreoElectronico(String correoElectronico);

    // Consulta para buscar proveedores por tipo de servicio
    List<Proveedor> findByTipoServicio(String tipoServicio);

    // Puedes agregar más consultas según tus necesidades

}
