package com.team4.payroll.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "apellido",nullable = true)
    private String apellido;

    @Column(name = "correo_electronico",nullable = false,unique = true)
    private String correoElectronico;

    @Column(name = "telefono",nullable = false)
    private String telefono;

    @Column(name = "direccion",nullable = true)
    private String direccion;

    @Column(name = "ciudad",nullable = true)
    private String ciudad;

    @Column(name = "codigo_postal",nullable = true)
    private String codigoPostal;

    
    @ManyToOne
    @JoinColumn(name = "empleadoId", referencedColumnName = "id")
    private Empleado empleado;

}
