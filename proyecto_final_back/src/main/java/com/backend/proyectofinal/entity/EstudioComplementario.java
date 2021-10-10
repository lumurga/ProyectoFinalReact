package com.backend.proyectofinal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="estudios")
public class EstudioComplementario {

    /* Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudio_sequence")
    @SequenceGenerator(name= "estudio_sequence", sequenceName = "estudio_sequence")
    @Column(name= "id_estudio")
    private Long id;
    private String nombre;
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odontologo_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Odontologo odontologo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Paciente paciente;

    /* Constructor */

    public EstudioComplementario() {
    }

    public EstudioComplementario(String nombre, LocalDate fecha, com.backend.proyectofinal.entity.Odontologo odontologo, com.backend.proyectofinal.entity.Paciente paciente) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    /* Getters y Setters */

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologoSolicitante(Odontologo odontolog) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /* Métodos */

    @Override
    public String toString() {
        return "Nombre: " +
                "Paciente: " + paciente +
                ", Odontólogo: " + odontologo +
                ", Fecha: " + fecha +
                '.';
    }
}
