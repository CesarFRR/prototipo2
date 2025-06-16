package com.example.component_2.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa la entidad Asignatura en MongoDB.
 * Cada instancia se almacena en la colección "asignaturas".
 */
@Document("asignaturas")
public class Asignatura {

    /** Identificador interno de MongoDB */
    @Id
    private ObjectId id;

    /** Nombre de la asignatura */
    private String nombre;

    /**
     * Lista de IDs de profesores asignados a esta asignatura.
     * Bidireccionalmente, cada Profesor guarda su asignaturaIds.
     */
    private List<ObjectId> profesorIds = new ArrayList<>();

    /** Constructor vacío requerido por Spring Data */
    public Asignatura() { }

    /**
     * Constructor de conveniencia.
     * @param nombre Nombre de la asignatura
     */
    public Asignatura(String nombre) {
        this.nombre = nombre;
    }

    // ------------------- Getters y Setters -------------------

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ObjectId> getProfesorIds() {
        return profesorIds;
    }

    public void setProfesorIds(List<ObjectId> profesorIds) {
        this.profesorIds = profesorIds;
    }
}
