package com.example.component_2.repository;

import com.example.component_2.model.Asignatura;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interfaz de acceso a datos para Asignaturas.
 * Extiende MongoRepository para operaciones CRUD.
 */
public interface AsignaturaRepository extends MongoRepository<Asignatura, ObjectId> {
    // Puedes añadir consultas adicionales si lo necesitas, p.ej.:
    // List<Asignatura> findByNombre(String nombre);
}
