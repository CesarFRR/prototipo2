package com.example.component_2.controller;

import com.example.component_2.model.Profesor;
import com.example.component_2.service.ProfesorService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Expone las operaciones GraphQL (Query y Mutation) para Profesores.
 */
@Controller
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    /**
     * Query: profesores
     * @return lista de todos los Profesores
     */
    @QueryMapping
    public List<Profesor> profesores() {
        return service.obtenerTodos();
    }

    /**
     * Query: profesorPorId
     * @param id ID del profesor en formato String
     * @return Profesor encontrado o null
     */
    @QueryMapping
    public Profesor profesorPorId(@Argument String id) {
        return service.obtenerPorId(id);
    }

    /**
     * Mutation: crearProfesor
     * @param nombre    Nombre completo
     * @param documento Documento único
     * @param area      Área del profesor
     * @return Profesor recién creado
     */
    @MutationMapping
    public Profesor crearProfesor(
        @Argument String nombre,
        @Argument String documento,
        @Argument String area
    ) {
        return service.crear(nombre, documento, area);
    }

    /**
     * Mutation: actualizarProfesor
     * @param id        ID del profesor a actualizar
     * @param nombre    Nuevo nombre (opcional)
     * @param area      Nueva área (opcional)
     * @return Profesor actualizado
     */
    @MutationMapping
    public Profesor actualizarProfesor(
        @Argument String id,
        @Argument String nombre,
        @Argument String area
    ) {
        return service.actualizar(id, nombre, area);
    }

    /**
     * Mutation: eliminarProfesor
     * @param id ID del profesor a eliminar
     * @return true si se eliminó correctamente
     */
    @MutationMapping
    public Boolean eliminarProfesor(@Argument String id) {
        return service.eliminar(id);
    }
}
