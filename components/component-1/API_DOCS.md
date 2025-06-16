# Documentación de la API REST - SIA Colegios

Esta documentación describe los endpoints disponibles en la API REST del Sistema de Información Académica para colegios.

## URL Base

```
http://localhost:8000/api/
```

## Autenticación

Actualmente, la API usa autenticación básica de Django REST Framework. Se pueden usar las credenciales del superusuario creado con `python manage.py createsuperuser`.

## Endpoints

### Cursos

#### Listar todos los cursos

- **URL:** `/api/cursos/`
- **Método:** `GET`
- **Descripción:** Obtiene un listado paginado de todos los cursos.
- **Respuesta exitosa:**
  ```json
  {
    "count": 5,
    "next": null,
    "previous": null,
    "results": [
      {
        "id": 1,
        "nombre": "Primero A",
        "codigo": "1-A"
      },
      {
        "id": 2,
        "nombre": "Segundo B",
        "codigo": "2-B"
      },
      ...
    ]
  }
  ```

#### Crear un nuevo curso

- **URL:** `/api/cursos/`
- **Método:** `POST`
- **Datos requeridos:**
  ```json
  {
    "nombre": "Décimo C",
    "codigo": "10-C"
  }
  ```
- **Respuesta exitosa:**
  ```json
  {
    "id": 6,
    "nombre": "Décimo C",
    "codigo": "10-C"
  }
  ```

#### Obtener detalles de un curso

- **URL:** `/api/cursos/{id}/`
- **Método:** `GET`
- **Descripción:** Obtiene los detalles de un curso específico.
- **Respuesta exitosa:**
  ```json
  {
    "id": 1,
    "nombre": "Primero A",
    "codigo": "1-A"
  }
  ```

#### Actualizar un curso

- **URL:** `/api/cursos/{id}/`
- **Método:** `PUT`
- **Datos requeridos:**
  ```json
  {
    "nombre": "Primero A actualizado",
    "codigo": "1-A"
  }
  ```
- **Respuesta exitosa:**
  ```json
  {
    "id": 1,
    "nombre": "Primero A actualizado",
    "codigo": "1-A"
  }
  ```

#### Actualizar parcialmente un curso

- **URL:** `/api/cursos/{id}/`
- **Método:** `PATCH`
- **Datos requeridos:** Solo los campos que se desean actualizar.
  ```json
  {
    "nombre": "Primero A modificado"
  }
  ```
- **Respuesta exitosa:**
  ```json
  {
    "id": 1,
    "nombre": "Primero A modificado",
    "codigo": "1-A"
  }
  ```

#### Eliminar un curso

- **URL:** `/api/cursos/{id}/`
- **Método:** `DELETE`
- **Descripción:** Elimina un curso específico.
- **Respuesta exitosa:** `204 No Content`

#### Obtener estudiantes de un curso

- **URL:** `/api/cursos/{id}/estudiantes/`
- **Método:** `GET`
- **Descripción:** Obtiene la lista de estudiantes asignados a un curso específico.
- **Respuesta exitosa:**
  ```json
  [
    {
      "id": 1,
      "nombre_completo": "Ana María López",
      "documento": "1001234567",
      "fecha_nacimiento": "2010-05-15",
      "acudiente": "María Rodríguez",
      "curso": 1,
      "fecha_registro": "2023-05-28T14:35:22.123456Z"
    },
    ...
  ]
  ```

### Estudiantes

#### Listar todos los estudiantes

- **URL:** `/api/estudiantes/`
- **Método:** `GET`
- **Descripción:** Obtiene un listado paginado de todos los estudiantes.
- **Respuesta exitosa:**
  ```json
  {
    "count": 6,
    "next": null,
    "previous": null,
    "results": [
      {
        "id": 1,
        "nombre_completo": "Ana María López",
        "documento": "1001234567",
        "fecha_nacimiento": "2010-05-15",
        "acudiente": "María Rodríguez",
        "curso": 1,
        "fecha_registro": "2023-05-28T14:35:22.123456Z"
      },
      ...
    ]
  }
  ```

#### Crear un nuevo estudiante

- **URL:** `/api/estudiantes/`
- **Método:** `POST`
- **Datos requeridos:**
  ```json
  {
    "nombre_completo": "Nuevo Estudiante",
    "documento": "1001234573",
    "fecha_nacimiento": "2010-01-01",
    "acudiente": "Acudiente Ejemplo",
    "curso": 1
  }
  ```
- **Respuesta exitosa:**
  ```json
  {
    "id": 7,
    "nombre_completo": "Nuevo Estudiante",
    "documento": "1001234573",
    "fecha_nacimiento": "2010-01-01",
    "acudiente": "Acudiente Ejemplo",
    "curso": 1,
    "fecha_registro": "2023-05-28T16:42:10.123456Z"
  }
  ```

#### Obtener detalles de un estudiante

- **URL:** `/api/estudiantes/{id}/`
- **Método:** `GET`
- **Descripción:** Obtiene los detalles de un estudiante específico, incluyendo información del curso.
- **Respuesta exitosa:**
  ```json
  {
    "id": 1,
    "nombre_completo": "Ana María López",
    "documento": "1001234567",
    "fecha_nacimiento": "2010-05-15",
    "acudiente": "María Rodríguez",
    "curso": {
      "id": 1,
      "nombre": "Primero A",
      "codigo": "1-A"
    },
    "fecha_registro": "2023-05-28T14:35:22.123456Z"
  }
  ```

#### Actualizar un estudiante

- **URL:** `/api/estudiantes/{id}/`
- **Método:** `PUT`
- **Datos requeridos:** Todos los campos del estudiante.
  ```json
  {
    "nombre_completo": "Ana María López Actualizado",
    "documento": "1001234567",
    "fecha_nacimiento": "2010-05-15",
    "acudiente": "María Rodríguez Nuevo",
    "curso": 2
  }
  ```
- **Respuesta exitosa:**
  ```json
  {
    "id": 1,
    "nombre_completo": "Ana María López Actualizado",
    "documento": "1001234567",
    "fecha_nacimiento": "2010-05-15",
    "acudiente": "María Rodríguez Nuevo",
    "curso": 2,
    "fecha_registro": "2023-05-28T14:35:22.123456Z"
  }
  ```

#### Actualizar parcialmente un estudiante

- **URL:** `/api/estudiantes/{id}/`
- **Método:** `PATCH`
- **Datos requeridos:** Solo los campos que se desean actualizar.
  ```json
  {
    "acudiente": "Nuevo Acudiente"
  }
  ```
- **Respuesta exitosa:**
  ```json
  {
    "id": 1,
    "nombre_completo": "Ana María López",
    "documento": "1001234567",
    "fecha_nacimiento": "2010-05-15",
    "acudiente": "Nuevo Acudiente",
    "curso": 1,
    "fecha_registro": "2023-05-28T14:35:22.123456Z"
  }
  ```

#### Eliminar un estudiante

- **URL:** `/api/estudiantes/{id}/`
- **Método:** `DELETE`
- **Descripción:** Elimina un estudiante específico.
- **Respuesta exitosa:** `204 No Content`

#### Filtrar estudiantes por código de curso

- **URL:** `/api/estudiantes/por_curso/?codigo=11-A`
- **Método:** `GET`
- **Descripción:** Obtiene un listado de estudiantes filtrados por el código del curso.
- **Respuesta exitosa:**
  ```json
  [
    {
      "id": 5,
      "nombre_completo": "Laura Valentina Gómez",
      "documento": "1001234571",
      "fecha_nacimiento": "2000-02-14",
      "acudiente": "Valentina Gómez",
      "curso": 5,
      "fecha_registro": "2023-05-28T14:35:22.123456Z"
    },
    {
      "id": 6,
      "nombre_completo": "Carlos Eduardo Sánchez",
      "documento": "1001234572",
      "fecha_nacimiento": "2000-07-30",
      "acudiente": "Eduardo Sánchez",
      "curso": 5,
      "fecha_registro": "2023-05-28T14:35:22.123456Z"
    }
  ]
  ```

## Parámetros de filtrado y búsqueda

### Cursos

- **Búsqueda:** `/api/cursos/?search=Primero`
  - Busca cursos cuyo nombre o código contenga el texto "Primero".
- **Ordenamiento:** `/api/cursos/?ordering=codigo`
  - Ordena los cursos por código de forma ascendente.
- **Ordenamiento descendente:** `/api/cursos/?ordering=-nombre`
  - Ordena los cursos por nombre de forma descendente.

### Estudiantes

- **Búsqueda:** `/api/estudiantes/?search=María`
  - Busca estudiantes cuyo nombre, documento o acudiente contenga el texto "María".
- **Ordenamiento:** `/api/estudiantes/?ordering=nombre_completo`
  - Ordena los estudiantes por nombre completo de forma ascendente.
- **Ordenamiento por fecha:** `/api/estudiantes/?ordering=-fecha_nacimiento`
  - Ordena los estudiantes por fecha de nacimiento de forma descendente (más recientes primero).

## Paginación

Todos los endpoints que devuelven múltiples resultados están paginados. 
La respuesta incluye los siguientes campos:

- `count`: Número total de resultados.
- `next`: URL para obtener la siguiente página de resultados (null si es la última).
- `previous`: URL para obtener la página anterior de resultados (null si es la primera).
- `results`: Array con los resultados de la página actual.

**Ejemplo:**

```json
{
  "count": 20,
  "next": "http://localhost:8000/api/estudiantes/?page=2",
  "previous": null,
  "results": [
    // Resultados de la primera página
  ]
}
```

Para solicitar una página específica, use el parámetro `page`:

```
/api/estudiantes/?page=2
```

El tamaño de página por defecto es 10, pero puede modificarse en la configuración del servidor. 