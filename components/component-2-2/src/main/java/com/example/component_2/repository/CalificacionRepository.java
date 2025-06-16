package com.example.component_2.repository;

import com.example.component_2.model.Calificacion;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * Interfaz de acceso a datos para Calificaciones.
 */
public interface CalificacionRepository extends MongoRepository<Calificacion, ObjectId> {

    List<Calificacion> findByEstudianteId(String estudianteId);
    List<Calificacion> findByAsignaturaId(ObjectId asignaturaId);
    List<Calificacion> findByCursoId(String cursoId);
    List<Calificacion> findByPeriodo(String periodo);

    // Métodos compuestos, por ejemplo:
    List<Calificacion> findByEstudianteIdAndPeriodo(String estudianteId, String periodo);
}
