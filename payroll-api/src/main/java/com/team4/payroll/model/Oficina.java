package com.team4.payroll.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "oficinas")
public class Oficina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "direccion",nullable = false)
    private String direccion;

    @Column(name = "ciudad",nullable = false)
    private String ciudad;

    @Column(name = "telefono",nullable = false)
    private String telefono;

    @Column(name = "codigo_postal",nullable = false)
    private String codigoPostal;

    @Column(name = "pais",nullable = false)
    private String pais;


    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;

    
}
