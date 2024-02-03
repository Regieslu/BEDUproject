package com.team4.payroll.repository;

import com.team4.payroll.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    // Consulta para buscar actividades por descripción
    List<Actividad> findByDescripcion(String descripcion);

    // Consulta para buscar actividades por rango de fechas de inicio
    List<Actividad> findByFechaInicioBetween(LocalDateTime fechaInicioInicio, LocalDateTime fechaInicioFin);

    // Consulta para buscar actividades por detalles
    List<Actividad> findByDetalles(String detalles);

    // Consulta para buscar actividades por descripción y detalles
    List<Actividad> findByDescripcionAndDetalles(String descripcion, String detalles);

    // Consulta para buscar actividades por descripción y fecha de inicio
    List<Actividad> findByDescripcionAndFechaInicio(String descripcion, LocalDateTime fechaInicio);
    

}
