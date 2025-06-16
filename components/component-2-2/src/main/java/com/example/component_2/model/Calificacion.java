package com.example.component_2.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa la entidad Calificacion en MongoDB.
 * Cada documento se almacena en la colección "calificaciones".
 */
@Document("calificaciones")
public class Calificacion {

    /** Identificador interno de MongoDB */
    @Id
    private ObjectId id;

    /** ID externo del estudiante (puede venir de tu BD SQL) */
    private String estudianteId;

    /** ID de la asignatura (referencia a colección "asignaturas") */
    private ObjectId asignaturaId;

    /** ID del curso, como string o ObjectId según tu diseño */
    private String cursoId;

    /** Periodo académico, p.ej. "2025-1" */
    private String periodo;

    /** Nota numérica, p.ej. 4.5 */
    private double nota;

    /** Observaciones opcionales */
    private String observaciones;

    public Calificacion() { }

    public Calificacion(
        String estudianteId,
        ObjectId asignaturaId,
        String cursoId,
        String periodo,
        double nota,
        String observaciones
    ) {
        this.estudianteId = estudianteId;
        this.asignaturaId = asignaturaId;
        this.cursoId = cursoId;
        this.periodo = periodo;
        this.nota = nota;
        this.observaciones = observaciones;
    }

    // ----- Getters y Setters -----

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(String estudianteId) {
        this.estudianteId = estudianteId;
    }

    public ObjectId getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(ObjectId asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public String getCursoId() {
        return cursoId;
    }

    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
