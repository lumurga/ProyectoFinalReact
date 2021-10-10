package com.backend.proyectofinal.service;


import com.backend.proyectofinal.entity.EstudioComplementario;
import com.backend.proyectofinal.repository.impl.IEstudioComplementarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class EstudioComplementarioService implements IEntidadService<EstudioComplementario> {


    private IEstudioComplementarioRepository estudioComplementarioRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;
    private static final Logger logger = Logger.getLogger(TurnoService.class);

    @Autowired

    public EstudioComplementarioService(IEstudioComplementarioRepository estudioComplementarioRepository) {
        this.estudioComplementarioRepository = estudioComplementarioRepository;
    }

    /* Métodos */
    public EstudioComplementario save(EstudioComplementario estudio) {
        return estudioComplementarioRepository.save(estudio);
    }

    public Optional<EstudioComplementario> findById(Long id) {
        return estudioComplementarioRepository.findById(id);
    }

    public List<EstudioComplementario> findAll() {
        return estudioComplementarioRepository.findAll();
    }

    public EstudioComplementario update(EstudioComplementario estudioNew) {
        EstudioComplementario estudio = estudioComplementarioRepository.findById(estudioNew.getId()).get();
        estudio.setNombre(estudioNew.getNombre());
        estudio.setFecha(estudioNew.getFecha());
        estudio.setOdontologoSolicitante(estudioNew.getOdontologo());
        estudio.setPaciente(estudioNew.getPaciente());
        estudioComplementarioRepository.save(estudio);
        return estudio;
    }

    public void delete(Long id) {
        if (estudioComplementarioRepository.findById(id).isPresent()) {
            estudioComplementarioRepository.deleteById(id);
            System.out.println("Eliminado con éxito!");
        } else {
            System.out.println("Estudio no encontrado!");
        }
    }

    /* Métodos útiles */

    public List<EstudioComplementario> findEstudiosByPacienteDni(Integer dni){
        return estudioComplementarioRepository.findEstudiosByPacienteDni(dni);
    }

    public List<EstudioComplementario> findEstudiosByOdontologoMatricula(String matricula){
        return estudioComplementarioRepository.findEstudiosByOdontologoMatricula(matricula);
    }

    public List<EstudioComplementario> listEstudiosByFecha(LocalDate fecha){
        return estudioComplementarioRepository.listEstudiosByFecha(fecha);
    }

}
