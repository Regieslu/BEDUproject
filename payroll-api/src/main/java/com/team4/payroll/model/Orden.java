package com.team4.payroll.model;

import java.util.Date;

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
@Table(name = "Orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "fecha",nullable = false)
    private Date fecha;

    @Column(name = "total",nullable = false)
    private double total;

    @Column(name = "estado")
    private String estado;

    @Column(name = "direccion_entrega",nullable = false)
    private String direccionEntrega;

    @Column(name = "metodo_pago",nullable = false)
    private String metodoPago;

    @ManyToOne
    @JoinColumn(name = "clienteId", referencedColumnName = "id",nullable = true)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "productoId", referencedColumnName = "id",nullable = true)
    private Producto producto;

}