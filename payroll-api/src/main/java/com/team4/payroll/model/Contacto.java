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
@Table(name = "Contacto")
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "apellido",nullable = true)
    private String apellido;

    @Column(name = "correo_electronico",nullable = false)
    private String correoElectronico;

    @Column(name = "telefono",nullable = false)
    private String telefono;

    @Column(name = "cargo",nullable = false)
    private String cargo;

    @Column(name = "departamento",nullable = true)
    private String departamento;

    @Column(name = "empresa",nullable = false)
    private String empresa;

    @Column(name = "direccion",nullable = true)
    private String direccion;

    @Column(name = "ciudad",nullable = true)
    private String ciudad;

    @Column(name = "pais",nullable = true)
    private String pais;

    
    @ManyToOne
    @JoinColumn(name = "empleadoId", referencedColumnName = "id")
    private Empleado empleado;
}
