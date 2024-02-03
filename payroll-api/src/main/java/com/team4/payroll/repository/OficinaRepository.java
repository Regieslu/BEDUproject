package com.team4.payroll.repository;

import com.team4.payroll.model.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {
    
    // Consulta para buscar una oficina por su nombre
    Oficina findByNombre(String nombre);

    // Consulta para buscar oficinas por ciudad
    List<Oficina> findByCiudad(String ciudad);

    // Consulta para buscar oficinas por código postal
    List<Oficina> findByCodigoPostal(String codigoPostal);

    // Consulta para buscar oficinas por país
    List<Oficina> findByPais(String pais);

    // Consulta para buscar oficinas por fecha de apertura
    List<Oficina> findByFechaApertura(LocalDate fechaApertura);

    // Puedes agregar más consultas según tus necesidades

}
