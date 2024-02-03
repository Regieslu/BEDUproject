package com.team4.payroll.repository;

import com.team4.payroll.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Consulta para buscar un cliente por su correo electrónico
    Cliente findByCorreoElectronico(String correoElectronico);

    // Consulta para buscar clientes por ciudad
    List<Cliente> findByCiudad(String ciudad);

    // Consulta para buscar clientes por código postal
    List<Cliente> findByCodigoPostal(String codigoPostal);

    // Consulta para buscar clientes por nombre y apellido
    List<Cliente> findByNombreAndApellido(String nombre, String apellido);

    // Consulta para buscar clientes por nombre o ciudad
    List<Cliente> findByNombreOrCiudad(String nombre, String ciudad);

}
