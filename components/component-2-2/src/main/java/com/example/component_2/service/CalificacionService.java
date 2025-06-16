package com.example.component_2.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.component_2.model.Calificacion;
import com.example.component_2.repository.CalificacionRepository;

/**
 * Lógica de negocio para Calificaciones: CRUD y filtrado.
 */
@Service
public class CalificacionService {

    private final CalificacionRepository repo;

    public CalificacionService(CalificacionRepository repo) {
        this.repo = repo;
    }

    /**
     * Lista todas las calificaciones, filtrando si se proveen parámetros.
     */
    public List<Calificacion> obtenerCalificaciones(
        String estudianteId,
        String asignaturaId,
        String cursoId,
        String periodo
    ) {
        if (estudianteId != null) {
            return repo.findByEstudianteId(estudianteId);
        }
        if (asignaturaId != null) {
            return repo.findByAsignaturaId(new ObjectId(asignaturaId));
        }
        if (cursoId != null) {
            return repo.findByCursoId(cursoId);
        }
        if (periodo != null) {
            return repo.findByPeriodo(periodo);
        }
        return repo.findAll();
    }

    /**
     * Registra una nueva calificación.
     */
    public Calificacion registrar(
        String estudianteId,
        String asignaturaId,
        String cursoId,
        String periodo,
        double nota,
        String observaciones
    ) {
        // Aquí podrías validar rangos de nota, existencia de asignatura, etc.
        Calificacion c = new Calificacion(
            estudianteId,
            new ObjectId(asignaturaId),
            cursoId,
            periodo,
            nota,
            observaciones
        );
        return repo.save(c);
    }

    /**
     * Actualiza nota y/o observaciones de una calificación existente.
     */
    public Calificacion actualizar(
        String id,
        Double nota,
        String observaciones
    ) {
        Calificacion c = repo.findById(new ObjectId(id))
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Calificación no encontrada"));
        if (nota != null) {
            c.setNota(nota);
        }
        if (observaciones != null) {
            c.setObservaciones(observaciones);
        }
        return repo.save(c);
    }

    /**
     * Elimina una calificación por su ID.
     */
    public boolean eliminar(String id) {
        Calificacion c = repo.findById(new ObjectId(id))
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Calificación no encontrada"));
        repo.delete(c);
        return true;
    }
}
