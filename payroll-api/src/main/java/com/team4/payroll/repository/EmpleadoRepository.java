package com.team4.payroll.repository;

import com.team4.payroll.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
    // Consulta para buscar un empleado por su email
    Empleado findByEmail(String email);

    // Consulta para buscar empleados por nombre
    List<Empleado> findByNombre(String nombre);

    // Consulta para buscar empleados por apellido
    List<Empleado> findByApellido(String apellido);

    // Consulta para buscar empleados por puesto
    List<Empleado> findByPuesto(String puesto);

    // Consulta para buscar empleados por departamento
    List<Empleado> findByDepartamento(String departamento);

    // Consulta para buscar empleados por nombre y apellido
    List<Empleado> findByNombreAndApellido(String nombre, String apellido);

    // Consulta para buscar empleados por nombre o puesto
    List<Empleado> findByNombreOrPuesto(String nombre, String puesto);


}
