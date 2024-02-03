package com.team4.payroll.repository;

import com.team4.payroll.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
    
    // Consulta para buscar un contacto por su correo electrónico
    Contacto findByCorreoElectronico(String correoElectronico);

    // Consulta para buscar contactos por empresa
    List<Contacto> findByEmpresa(String empresa);

    // Consulta para buscar contactos por ciudad
    List<Contacto> findByCiudad(String ciudad);

    // Consulta para buscar contactos por cargo
    List<Contacto> findByCargo(String cargo);

    // Consulta para buscar contactos por departamento
    List<Contacto> findByDepartamento(String departamento);

    // Consulta para buscar contactos por nombre y apellido
    List<Contacto> findByNombreAndApellido(String nombre, String apellido);

    // Consulta para buscar contactos por nombre o empresa
    List<Contacto> findByNombreOrEmpresa(String nombre, String empresa);


}
