package com.example.component_2.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa la entidad Profesor en MongoDB.
 * Cada instancia se almacena en la colección "profesores".
 */
@Document("profesores")
public class Profesor {

    /** Identificador interno de MongoDB */
    @Id
    private ObjectId id;

    /** Nombre completo del profesor */
    private String nombre;

    /**
     * Documento de identidad del profesor.
     * Índice único para evitar duplicados.
     */
    @Indexed(unique = true)
    private String documento;

    /** Área o departamento al que pertenece el profesor */
    private String area;

    /** Constructor vacío requerido por Spring Data */
    public Profesor() { }

    /**
     * Constructor de conveniencia.
     * @param nombre    Nombre completo
     * @param documento Documento de identidad
     * @param area      Área o departamento
     */
    public Profesor(String nombre, String documento, String area) {
        this.nombre = nombre;
        this.documento = documento;
        this.area = area;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
