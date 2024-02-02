package com.team4.payroll.model;

import java.util.Date;

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
@Table(name = "Orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrden")
    private int idOrden;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "total")
    private double total;

    @Column(name = "estado")
    private String estado;

    @Column(name = "direccion_entrega")
    private String direccionEntrega;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @Column(name = "numero_tarjeta")
    private String numeroTarjeta;

    @Column(name = "fecha_expiracion_tarjeta")
    private Date fechaExpiracionTarjeta;

    @Column(name = "codigo_seguridad_tarjeta")
    private String codigoSeguridadTarjeta;

    // Otros campos y relaciones
}