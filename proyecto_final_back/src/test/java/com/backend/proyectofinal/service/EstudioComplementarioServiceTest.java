package com.backend.proyectofinal.service;



import com.backend.proyectofinal.entity.*;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EstudioComplementarioServiceTest {

    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private EstudioComplementarioService estudioComplementarioService;

    Domicilio dDefault;
    Paciente pDefault;
    Odontologo oDefault;
    EstudioComplementario eDefault;

    @BeforeEach
    void init(){
        dDefault = new Domicilio("Wallaby", "42", "Sydney", "Sydney");
        oDefault = new Odontologo("Cristina", "Albornoz", 18346540);
        pDefault = new Paciente("Luciana", "Murga", 31356178, LocalDate.now(), dDefault);
        eDefault = new EstudioComplementario("Radiografía Periapical", LocalDate.now(), oDefault,pDefault);
    }

    @Test
    @Order(1)
    @DisplayName("Insertar registro -> Estudio Complementario")
    void saveEstudio(){
        Odontologo od = odontologoService.save(oDefault);
        Paciente pac = pacienteService.save(pDefault);
        EstudioComplementario tmp = new EstudioComplementario("Radiografía Periapical", LocalDate.now(), oDefault,pDefault);
        EstudioComplementario est = estudioComplementarioService.save(tmp);
        Assertions.assertNotNull(est);
        Assertions.assertNotNull(est.getId());
        Assertions.assertEquals(est.getPaciente(), pDefault);
        Assertions.assertEquals(est.getOdontologo(), oDefault);
    }



    @Test
    @Order(2)
    @DisplayName("Búsqueda por ID -> Estudio Complementario")
    void findEstudioById() {
        EstudioComplementario est = estudioComplementarioService.findById(1L).orElse(null);
        Assertions.assertNotNull(est);
        Assertions.assertNotNull(est.getId());
        Assertions.assertTrue(est instanceof EstudioComplementario);
    }


    @Test
    @Order(3)
    @DisplayName("Eliminar registro -> Estudio Complementario")
    void deleteEstudio() {
        EstudioComplementario est = estudioComplementarioService.findById(1L).orElse(null);
        Assertions.assertNotNull(est);
        pacienteService.delete(est.getId());
        EstudioComplementario resultado = estudioComplementarioService.findById(1L).orElse(null);Assertions.assertNull(resultado);
    }
}