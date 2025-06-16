package com.example.component_2.controller;

import com.example.component_2.model.Calificacion;
import com.example.component_2.service.CalificacionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Expone las operaciones GraphQL (Query y Mutation) para Calificaciones.
 */
@Controller
public class CalificacionController {

    private final CalificacionService service;

    public CalificacionController(CalificacionService service) {
        this.service = service;
    }

    /**
     * Query: calificaciones(...)
     */
    @QueryMapping
    public List<Calificacion> calificaciones(
        @Argument String estudianteId,
        @Argument String asignaturaId,
        @Argument String cursoId,
        @Argument String periodo
    ) {
        return service.obtenerCalificaciones(estudianteId, asignaturaId, cursoId, periodo);
    }

    /**
     * Mutation: registrarCalificacion(...)
     */
    @MutationMapping
    public Calificacion registrarCalificacion(
        @Argument String estudianteId,
        @Argument String asignaturaId,
        @Argument String cursoId,
        @Argument String periodo,
        @Argument double nota,
        @Argument String observaciones
    ) {
        return service.registrar(estudianteId, asignaturaId, cursoId, periodo, nota, observaciones);
    }

    /**
     * Mutation: actualizarCalificacion(...)
     */
    @MutationMapping
    public Calificacion actualizarCalificacion(
        @Argument String id,
        @Argument Double nota,
        @Argument String observaciones
    ) {
        return service.actualizar(id, nota, observaciones);
    }

    /**
     * Mutation: eliminarCalificacion(...)
     */
    @MutationMapping
    public Boolean eliminarCalificacion(@Argument String id) {
        return service.eliminar(id);
    }
}
