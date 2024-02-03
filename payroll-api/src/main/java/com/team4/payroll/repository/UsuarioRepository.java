package com.team4.payroll.repository;

import com.team4.payroll.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Consulta para buscar un usuario por su correo electrónico
    Usuario findByCorreoElectronico(String correoElectronico);

    // Consulta para buscar un usuario por su nombre de usuario
    Usuario findByNombreUsuario(String nombreUsuario);

    // Consulta para buscar usuarios por nombre y apellido
    List<Usuario> findByNombreAndApellido(String nombre, String apellido);

    // Consulta para buscar usuarios por nombre de usuario y contraseña
    Usuario findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);

    // Puedes agregar más consultas según tus necesidades

}
