package com.team4.payroll.repository;

import com.team4.payroll.model.Empleado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EmpleadoRepositoryTest {

    @Autowired
    private EmpleadoRepository repository;
    @Autowired
    private TestEntityManager manager;

    @Test
    @DisplayName("Repository should be injected")
    void smokeTest(){
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Repository should be filter employees by name")
    void findByNameTest(){
        Empleado empleado1 =  new Empleado();
        Empleado empleado2 =  new Empleado();
        Empleado empleado3 =  new Empleado();

        empleado1.setNombre("Cristian");
        empleado1.setApellido("Padilla");

        empleado2.setNombre("Gael");
        empleado2.setApellido("Ramirez");

        empleado3.setNombre("Karla");
        empleado3.setApellido("Esquivel");

        manager.persist(empleado1);
        manager.persist(empleado2);
        manager.persist(empleado3);

        Empleado empleado = null;

        Optional<Empleado> repositoryByName = repository.findById(1L);

        if (repositoryByName.isPresent()) {
            empleado = repositoryByName.get();
        }

        assertNotNull(empleado);
        assertEquals("Cristian", empleado.getNombre());
    }
}
