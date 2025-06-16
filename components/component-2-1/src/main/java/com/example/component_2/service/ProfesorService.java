package com.example.component_2.service;

import com.example.component_2.model.Profesor;
import com.example.component_2.repository.ProfesorRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Capa de servicio que aplica lógica de negocio sobre Profesores.
 */
@Service
public class ProfesorService {

    private final ProfesorRepository repo;

    public ProfesorService(ProfesorRepository repo) {
        this.repo = repo;
    }

    /**
     * Recupera todos los profesores.
     * @return lista de Profesores
     */
    public List<Profesor> obtenerTodos() {
        return repo.findAll();
    }

    /**
     * Obtiene un profesor por su ID.
     * @param id Cadena con el ObjectId de Mongo
     * @return Profesor encontrado
     * @throws ResponseStatusException (404) si no existe
     */
    public Profesor obtenerPorId(String id) {
        try {
            ObjectId oid = new ObjectId(id);
            return repo.findById(oid)
                       .orElseThrow(() -> 
                         new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado"));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID de Mongo inválido");
        }
    }

    /**
     * Crea un nuevo profesor validando unicidad de documento.
     * @param nombre    Nombre completo
     * @param documento Documento único
     * @param area      Área correspondiente
     * @return Profesor guardado
     * @throws ResponseStatusException (409) si el documento ya existe
     */
    public Profesor crear(String nombre, String documento, String area) {
        if (repo.existsByDocumento(documento)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, 
                                              "Ya existe un profesor con ese documento");
        }
        Profesor p = new Profesor(nombre, documento, area);
        return repo.save(p);
    }

    /**
     * Actualiza campos nombre y/o área de un profesor existente.
     * @param id     ID del profesor
     * @param nombre Nuevo nombre (opcional)
     * @param area   Nueva área (opcional)
     * @return Profesor actualizado
     */
    public Profesor actualizar(String id, String nombre, String area) {
        Profesor p = obtenerPorId(id);
        if (nombre != null && !nombre.isBlank()) {
            p.setNombre(nombre);
        }
        if (area != null && !area.isBlank()) {
            p.setArea(area);
        }
        return repo.save(p);
    }

    /**
     * Elimina un profesor por su ID.
     * @param id ID del profesor
     * @return true si la operación fue exitosa
     */
    public boolean eliminar(String id) {
        Profesor p = obtenerPorId(id);
        repo.delete(p);
        return true;
    }
}
