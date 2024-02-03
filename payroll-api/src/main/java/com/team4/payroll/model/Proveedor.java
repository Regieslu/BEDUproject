package com.team4.payroll.model;

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
@Table(name = "Proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "direccion",nullable = false)
    private String direccion;

    @Column(name = "telefono",nullable = false)
    private String telefono;

    @Column(name = "correo_electronico",nullable = false,unique = true)
    private String correoElectronico;

    @Column(name = "tipo_servicio",nullable = false)
    private String tipoServicio;
    
}
