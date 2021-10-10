package com.backend.proyectofinal.controller;


import com.backend.proyectofinal.entity.EstudioComplementario;
import com.backend.proyectofinal.service.EstudioComplementarioService;
import com.backend.proyectofinal.service.OdontologoService;
import com.backend.proyectofinal.service.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/estudios")
public class EstudioComplementarioController {

    /* Atributos */
    private  EstudioComplementarioService estudioComplementarioService;

    private PacienteService pacienteService;

    private OdontologoService odontologoService;

    private static final Logger logger = Logger.getLogger(String.valueOf(TurnoController.class));



    /* Constructor */
    @Autowired
    public EstudioComplementarioController(EstudioComplementarioService estudioComplementarioService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.estudioComplementarioService = estudioComplementarioService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    /* GET */
    @GetMapping("/{id}")
    public EstudioComplementario findEstudioById(@PathVariable Long id) {
        return estudioComplementarioService.findById(id).orElse(null);
    }

    @GetMapping
    public List<EstudioComplementario> findAllEstudios() {
        return estudioComplementarioService.findAll();
    }

    @GetMapping("/{fecha}")
    public List<EstudioComplementario> listEstudiosByFecha(@PathVariable LocalDate fecha){
        logger.info("Listado de los Estudios según la fecha");
        return estudioComplementarioService.listEstudiosByFecha(fecha);
    }

    @GetMapping("/{dni}")
    public List<EstudioComplementario> findEstudiosByPacienteDni(@PathVariable Integer dni){
        return estudioComplementarioService.findEstudiosByPacienteDni(dni);
    }

    @GetMapping("/{matricula}")
    public List<EstudioComplementario> findEstudiosByOdontologoMatricula(@PathVariable String matricula){
        return estudioComplementarioService.findEstudiosByOdontologoMatricula(matricula);
    }


    /* POST */
    @PostMapping("/registro")
    public ResponseEntity saveEstudio(@RequestBody EstudioComplementario estudioComplementario) {
        ResponseEntity response;

        if(estudioComplementarioService.findById(estudioComplementario.getOdontologo().getId()).isEmpty()) {
            response = new ResponseEntity("Odontólogo no encontrado!", HttpStatus.NOT_FOUND);
        } else if (pacienteService.findById(estudioComplementario.getPaciente().getId()).isEmpty()){
            response = new ResponseEntity("Paciente no encontrado!", HttpStatus.NOT_FOUND);
        }else {
            response = new ResponseEntity(estudioComplementarioService.save(estudioComplementario), HttpStatus.OK);
        }
        return response;
    }



    /* PUT */
    @PutMapping("/modificar")
    public ResponseEntity update(@RequestBody EstudioComplementario modificacion) {
        ResponseEntity respuesta;

        if(estudioComplementarioService.findById(modificacion.getId()).isPresent()) {
            respuesta = new ResponseEntity(estudioComplementarioService.update(modificacion), HttpStatus.OK);
        } else {
            respuesta = new ResponseEntity("Estudio no encontrado!", HttpStatus.NOT_FOUND);
        }
        return respuesta;
    }


    /* DELETE */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteEstudio(@PathVariable Long id) {
        ResponseEntity response;

        if(estudioComplementarioService.findById(id).isPresent()) {
            estudioComplementarioService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Estudio eliminado correctamente!");
        } else {
            response = new ResponseEntity("Estudio no encontrado!", HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
