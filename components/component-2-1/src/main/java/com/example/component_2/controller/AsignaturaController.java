package com.example.component_2.controller;

import com.example.component_2.model.Asignatura;
import com.example.component_2.service.AsignaturaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Expone las operaciones GraphQL (Query y Mutation) para Asignaturas.
 */
@Controller
public class AsignaturaController {

    private final AsignaturaService service;

    public AsignaturaController(AsignaturaService service) {
        this.service = service;
    }

    /** Query: listas todas las asignaturas */
    @QueryMapping
    public List<Asignatura> asignaturas() {
        return service.obtenerTodas();
    }

    /** Mutation: crea asignatura */
    @MutationMapping
    public Asignatura crearAsignatura(@Argument String nombre) {
        return service.crear(nombre);
    }

    /** Mutation: actualiza nombre */
    @MutationMapping
    public Asignatura actualizarAsignatura(
        @Argument String id,
        @Argument String nombre
    ) {
        return service.actualizar(id, nombre);
    }

    /** Mutation: elimina asignatura */
    @MutationMapping
    public Boolean eliminarAsignatura(@Argument String id) {
        return service.eliminar(id);
    }

    /** Mutation: asigna profesor a asignatura */
    @MutationMapping
    public Asignatura asignarProfesorAAsignatura(
        @Argument String profesorId,
        @Argument String asignaturaId
    ) {
        return service.asignarProfesor(asignaturaId, profesorId);
    }

    /** Mutation: desasigna profesor */
    @MutationMapping
    public Asignatura desasignarProfesorDeAsignatura(
        @Argument String profesorId,
        @Argument String asignaturaId
    ) {
        return service.desasignarProfesor(asignaturaId, profesorId);
    }
}
