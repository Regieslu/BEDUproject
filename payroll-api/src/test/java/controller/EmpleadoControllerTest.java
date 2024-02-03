package controller;

import com.team4.payroll.controller.EmpleadoController;
import com.team4.payroll.dto.CreateEmpleadoDTO;
import com.team4.payroll.dto.EmpleadoDTO;
import com.team4.payroll.dto.UpdateEmpleadoDTO;
import com.team4.payroll.exception.EmpleadoNotFoundException;
import com.team4.payroll.service.EmpleadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest (classes = EmpleadoController.class)
public class EmpleadoControllerTest {

    @MockBean
    private EmpleadoService service;
    @Autowired
    private EmpleadoController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest(){
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller should return a list of employees")
    void findAllTest(){
        List<EmpleadoDTO> data = new LinkedList<>();

        EmpleadoDTO empleado = new EmpleadoDTO();

        empleado.setId(234);
        empleado.setApellido("Perez");
        empleado.setEmail("perez@mail.com");
        empleado.setDepartamento("ventas");
        empleado.setPuesto("gerente");

        data.add(empleado);

        when(service.findAll()).thenReturn(data);


       List<EmpleadoDTO> result = controller.findAll();

       assertNotNull(result);
       assertTrue(result.size() > 0);
       assertEquals(empleado.getId(), result.get(0).getId());
       assertEquals(empleado.getApellido(), result.get(0).getApellido());
       assertEquals(empleado.getEmail(), result.get(0).getEmail());
       assertEquals(empleado.getDepartamento(), result.get(0).getDepartamento());
       assertEquals(empleado.getPuesto(), result.get(0).getPuesto());

    }
    @Test
    @DisplayName("Controller should be save a employee")
    void saveTest(){
        CreateEmpleadoDTO dto = new CreateEmpleadoDTO();
        dto.setNombre("Fernanda");
        dto.setApellido("Perea");

        EmpleadoDTO empleado = new EmpleadoDTO();

        empleado.setId(345);
        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setDepartamento(dto.getDepartamento());
        empleado.setPuesto(dto.getPuesto());
        empleado.setEmail(dto.getEmail());

        when(service.save(any(CreateEmpleadoDTO.class))).thenReturn(empleado);

        EmpleadoDTO result = controller.save(dto);
        assertNotNull(result);
        assertEquals(empleado.getId(), result.getId());
        assertEquals(empleado.getNombre(), result.getNombre());
        assertEquals(empleado.getApellido(), result.getApellido());
        assertEquals(empleado.getDepartamento(), result.getDepartamento());
        assertEquals(empleado.getPuesto(), result.getPuesto());
        assertEquals(empleado.getEmail(), result.getEmail());
    }

    @Test
    @DisplayName("Controller should update an employee")
    void updateTest() throws EmpleadoNotFoundException {
        UpdateEmpleadoDTO dto = new UpdateEmpleadoDTO();

        dto.setNombre("Julio");
        dto.setApellido("Fernandez");

        controller.update(400L, dto);

        verify(service, times(1)).update(400L, dto);
    }

    @Test
    @DisplayName("Controller should delete a employee")
    void deleteByIdTest() throws EmpleadoNotFoundException {
        controller.deleteById(5688L);

        verify(service, times(1)).deleteById(5688L);

    }
}
