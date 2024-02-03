package com.team4.payroll.repository;

import com.team4.payroll.model.Orden;
import com.team4.payroll.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    
    // Consulta para buscar órdenes por cliente
    List<Orden> findByCliente(Cliente cliente);

    // Consulta para buscar órdenes por estado
    List<Orden> findByEstado(String estado);

    // Consulta para buscar órdenes por método de pago
    List<Orden> findByMetodoPago(String metodoPago);


}
