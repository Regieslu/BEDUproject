package com.team4.payroll.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Actividad")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "descripcion",nullable = false)
    private String descripcion;

    @Column(name = "fechaInicio",nullable = true)
    private LocalDateTime fechaInicio;

    @Column(name = "fechaFin",nullable = true)
    private LocalDateTime fechaFin;

    @Column(name = "detalles",nullable = false)
    private String detalles;

    @ManyToOne
    @JoinColumn(name = "empleadoId", referencedColumnName = "id")
    private Empleado empleado;

}
