package com.example.component_2.service;

import com.example.component_2.model.Asignatura;
import com.example.component_2.repository.AsignaturaRepository;
import com.example.component_2.repository.ProfesorRepository;
import com.example.component_2.model.Profesor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Capa de servicio que aplica lógica de negocio sobre Asignaturas,
 * incluyendo la asociación/desasociación con Profesores.
 */
@Service
public class AsignaturaService {

    private final AsignaturaRepository asignRepo;
    private final ProfesorRepository profRepo;

    public AsignaturaService(AsignaturaRepository asignRepo,
                             ProfesorRepository profRepo) {
        this.asignRepo = asignRepo;
        this.profRepo = profRepo;
    }

    /** Lista todas las asignaturas */
    public List<Asignatura> obtenerTodas() {
        return asignRepo.findAll();
    }

    /** Obtiene una asignatura por su ID */
    public Asignatura obtenerPorId(String id) {
        try {
            ObjectId oid = new ObjectId(id);
            return asignRepo.findById(oid)
                .orElseThrow(() -> 
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no encontrada"));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID de Mongo inválido");
        }
    }

    /**
     * Crea una asignatura nueva.
     * @param nombre Nombre de la asignatura
     */
    public Asignatura crear(String nombre) {
        Asignatura a = new Asignatura(nombre);
        return asignRepo.save(a);
    }

    /**
     * Actualiza el nombre de una asignatura.
     * @param id     ID de la asignatura
     * @param nombre Nuevo nombre (si no es null/blank)
     */
    public Asignatura actualizar(String id, String nombre) {
        Asignatura a = obtenerPorId(id);
        if (nombre != null && !nombre.isBlank()) {
            a.setNombre(nombre);
        }
        return asignRepo.save(a);
    }

    /**
     * Elimina una asignatura por su ID.
     * @param id ID de la asignatura
     */
    public boolean eliminar(String id) {
        Asignatura a = obtenerPorId(id);
        asignRepo.delete(a);
        return true;
    }

    /**
     * Asocia un profesor a la asignatura (bidireccional).
     */
    public Asignatura asignarProfesor(String asignaturaId, String profesorId) {
        Asignatura a = obtenerPorId(asignaturaId);
        Profesor p = profRepo.findById(new ObjectId(profesorId))
            .orElseThrow(() -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado"));

        ObjectId pid = p.getId();
        if (!a.getProfesorIds().contains(pid)) {
            a.getProfesorIds().add(pid);
            asignRepo.save(a);
        }
        // También podrías actualizar p.getAsignaturaIds() y guardar al profesor
        return a;
    }

    /**
     * Desasocia un profesor de la asignatura.
     */
    public Asignatura desasignarProfesor(String asignaturaId, String profesorId) {
        Asignatura a = obtenerPorId(asignaturaId);
        ObjectId pid = new ObjectId(profesorId);
        if (a.getProfesorIds().remove(pid)) {
            asignRepo.save(a);
        }
        return a;
    }
}
