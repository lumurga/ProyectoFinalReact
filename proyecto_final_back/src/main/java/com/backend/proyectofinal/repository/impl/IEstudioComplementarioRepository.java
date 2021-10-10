package com.backend.proyectofinal.repository.impl;

import com.backend.proyectofinal.entity.EstudioComplementario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IEstudioComplementarioRepository extends JpaRepository<EstudioComplementario, Long> {

    @Query("SELECT e FROM Estudio e WHERE e.paciente.dni = ?1")
    List<EstudioComplementario> findEstudiosByPacienteDni(Integer dni);

    @Query("SELECT e FROM Estudio e WHERE e.odontologo.matricula  = ?1")
    List<EstudioComplementario> findEstudiosByOdontologoMatricula(String matricula);

    @Query("SELECT e FROM Estudio e WHERE e.fecha = ?1")
    List<EstudioComplementario> listEstudiosByFecha(LocalDate fecha);
}
