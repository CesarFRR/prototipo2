# Component-2-2: Spring Boot + GraphQL Microservice calificaciones

Este microservicio implementa la gestión de  **Calificaciones** mediante un API GraphQL y MongoDB.

## 📋 Funcionalidades

### Calificación

* **Query**:

  * `calificaciones(estudianteId: ID, asignaturaId: ID, cursoId: ID, periodo: String): [Calificacion!]!`

  **Ejemplo**:

  ```graphql
  query {
    calificaciones(periodo: "2025-1") {
      id
      estudianteId
      asignaturaId
      cursoId
      periodo
      nota
      observaciones
    }
  }
  ```

* **Mutation**:

  * `registrarCalificacion(estudianteId: ID!, asignaturaId: ID!, cursoId: ID!, periodo: String!, nota: Float!, observaciones: String): Calificacion!`
  * `actualizarCalificacion(id: ID!, nota: Float, observaciones: String): Calificacion!`
  * `eliminarCalificacion(id: ID!): Boolean!`

  **Ejemplos**:

  ```graphql
  mutation {
    registrarCalificacion(
      estudianteId: "est-101",
      asignaturaId: "<ID_ASIG>",
      cursoId: "11-A",
      periodo: "2025-1",
      nota: 4.2,
      observaciones: "Buen desempeño"
    ) {
      id nota observaciones
    }
  }

  mutation {
    actualizarCalificacion(
      id: "<ID_CALIF>",
      nota: 4.8
    ) {
      id nota observaciones
    }
  }

  mutation {
    eliminarCalificacion(id: "<ID_CALIF>")
  }
  ```

## 🚀 Ejecución con Docker Compose con Docker Compose

1. **Clona el repositorio** y navega al directorio:

   ```bash
   git clone <URL_DEL_REPO>
   cd component-2-2
   ```

2. **Asegúrate** de no tener MongoDB local escuchando en el puerto 27017, o ajusta el puerto en `docker-compose.yml`.

3. **Levanta los servicios**:

   ```bash
   docker compose up --build
   ```

   * El servicio **mongoDB** correrá en el contenedor `mongoDB2` y se mapea al puerto 27019 (ajustable).
   * El servicio **api** correrá en `http://localhost:8081`.

4. **Probar GraphQL**:

   * Abre GraphiQL en: `http://localhost:8081/graphiql`
   * Ejecuta consultas y mutaciones según las funcionalidades descritas.

---
[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/Swarch2F/component-2)