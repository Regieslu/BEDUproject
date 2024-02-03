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
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "descripcion",nullable = false)
    private String descripcion;

    @Column(name = "precio",nullable = false)
    private double precio;

    @Column(name = "cantidadStock",nullable = false)
    private int cantidadStock;

    @ManyToOne
    @JoinColumn(name = "proveedorId", referencedColumnName = "id",nullable = true)
    private Proveedor proveedor;

}
