package com.backend.proyectofinal.controller;


import com.backend.proyectofinal.entity.*;
import com.backend.proyectofinal.service.EstudioComplementarioService;
import com.backend.proyectofinal.service.OdontologoService;
import com.backend.proyectofinal.service.PacienteService;
import com.backend.proyectofinal.service.TurnoService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EstudioComplementarioControllerTest {

    @Autowired
    private MockMvc mockMvc;
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

        dDefault = new Domicilio("Falsa", "123", "Córdoba", "Córdoba");

        oDefault = new Odontologo("Patricia", "Damiani", 65781);

        pDefault = new Paciente("Luciana", "Murga", 31356178, LocalDate.now(), dDefault);
        eDefault = new EstudioComplementario("Radiografía panorámica", LocalDate.now(), oDefault,pDefault);
    }
/*
    @Test
    @Order(1)
    @DisplayName("Insertar registro mediante API en la tabla Estudio Complementario")
    void saveEstudio(){

        Odontologo odb = odontologoService.save(oDefault);
        Paciente pdb = pacienteService.save(pDefault);
        EstudioComplementario tmp = new EstudioComplementario("Radiografía Panorámica", LocalDate.now(), odb, pdb);

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/estudios/registro")
                            .content(asJsonString(tmp))
                            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200))
                    .andExpect(content().contentType("application/json"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @Order(2)
    @DisplayName("Actualizar registro mediante API en la tabla Estudio Complementario")
    void updateEstudio() {

        EstudioComplementario est = estudioComplementarioService.findById(1L).orElse(null);
        Assertions.assertNotNull(est);
        est.setFecha(LocalDate.now());

        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/estudios/modificar")
                            .content(asJsonString(estudioComplementarioService.update(est)))
                            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200))
                    .andExpect(content().string("Estudio con ID: " + est.getId() + " ha sido actualizado"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    @Test
    @Order(3)
    @DisplayName("Listar mediante API todos los registros de la tabla Estudio Complementario")
    public void listAllEstudios() throws Exception {
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.get("/estudios")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

    }

    public static String asJsonString(Object object){
        try{
            ObjectMapper objectMapper = getObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return objectMapper.writeValueAsString(object);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }



    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper().registerModule(new ParameterNamesModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }
}