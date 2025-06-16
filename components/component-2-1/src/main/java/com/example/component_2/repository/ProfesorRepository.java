package com.example.component_2.repository;

import com.example.component_2.model.Profesor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

/**
 * Interfaz de acceso a datos para Profesores.
 * Extiende MongoRepository para CRUD básico y permite consultas por documento.
 */
public interface ProfesorRepository extends MongoRepository<Profesor, ObjectId> {

    /**
     * Busca un profesor por su documento de identidad.
     * @param documento Documento a buscar
     * @return Optional con el Profesor si existe
     */
    Optional<Profesor> findByDocumento(String documento);

    /**
     * Comprueba si ya existe un profesor con el documento dado.
     * @param documento Documento a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByDocumento(String documento);
}
