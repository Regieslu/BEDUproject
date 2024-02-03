package com.team4.payroll.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;


@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "apellido",nullable = false)
    private String apellido;

    @Column(name = "correoElectronico",nullable = false,unique = true)
    private String correoElectronico;

    @Column(name = "nombreUsuario",nullable = true)
    private String nombreUsuario;

    @Column(name = "contrasena",nullable = false)
    private String contrasena;

    @Column(name = "isAdmin", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isAdmin;
    

}