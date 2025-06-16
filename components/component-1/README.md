# SIA Colegios - Microservicio de Gestión Académica

Este microservicio forma parte del Sistema de Información Académica (SIA) para colegios, proporcionando una API REST para la gestión de estudiantes y cursos.

## 🚀 Características Principales

- **API REST completa** para la gestión de estudiantes y cursos
- **Persistencia en PostgreSQL** con soporte para caracteres especiales
- **Contenerización con Docker** para facilitar el despliegue
- **Documentación de API integrada**
- **CRUD completo** para todas las entidades

## 💻 Tecnologías

- **Backend:**
  - Django 4.2.x
  - Django REST Framework 3.14.x
  - PostgreSQL 17
  - Gunicorn para producción

- **Contenerización:**
  - Docker
  - Docker Compose

## 🐳 Despliegue con Docker

La forma más rápida y recomendada para ejecutar este microservicio es utilizando Docker.

### Requisitos previos

- Docker y Docker Compose instalados
- Git instalado

### Pasos para el despliegue

1. Clona este repositorio:
   ```bash
   git clone <repositorio>
   cd sia_colegios
   ```

2. Dale permisos de ejecución al script de gestión:
   ```bash
   chmod +x manage.sh
   ```

3. Inicia los contenedores:
   ```bash
   ./manage.sh start
   ```

4. La aplicación estará disponible en:
   - API REST: http://localhost:8000/api/
   - Administración: http://localhost:8000/admin/

### Comandos útiles

El script `manage.sh` proporciona varios comandos útiles:

```bash
./manage.sh start    # Iniciar los contenedores
./manage.sh stop     # Detener los contenedores
./manage.sh restart  # Reiniciar los contenedores
./manage.sh logs     # Ver los logs
./manage.sh clean    # Limpiar todo (incluyendo volúmenes)
./manage.sh help     # Mostrar ayuda
```

### Solución de problemas comunes

1. **Error de conexión a PostgreSQL**
   - Verifica que los contenedores estén corriendo: `docker-compose ps`
   - Revisa los logs: `./manage.sh logs`
   - Limpia todo y vuelve a intentar: `./manage.sh clean && ./manage.sh start`

2. **Error de permisos**
   - Asegúrate de que el script `manage.sh` tenga permisos de ejecución
   - En Windows, ejecuta PowerShell como administrador

3. **Error de puertos en uso**
   - Verifica que los puertos 8000 y 5432 no estén en uso
   - Puedes cambiar los puertos en el `docker-compose.yml`

4. **Error de datos duplicados**
   - Limpia los volúmenes: `./manage.sh clean`
   - Reinicia los contenedores: `./manage.sh start`

### Variables de entorno

Puedes personalizar la configuración mediante variables de entorno en el `docker-compose.yml`:

- `DB_HOST`: Host de la base de datos (por defecto: db)
- `DB_NAME`: Nombre de la base de datos (por defecto: sia_colegios)
- `DB_USER`: Usuario de la base de datos (por defecto: postgres)
- `DB_PASSWORD`: Contraseña de la base de datos (por defecto: postgres)
- `DB_PORT`: Puerto de la base de datos (por defecto: 5432)

## 📚 API REST

### Endpoints principales

#### Cursos
- `GET /api/cursos/` - Listar todos los cursos
- `POST /api/cursos/` - Crear un nuevo curso
- `GET /api/cursos/{id}/` - Obtener detalles de un curso
- `PUT /api/cursos/{id}/` - Actualizar un curso
- `DELETE /api/cursos/{id}/` - Eliminar un curso
- `GET /api/cursos/{id}/estudiantes/` - Obtener estudiantes de un curso

#### Estudiantes
- `GET /api/estudiantes/` - Listar todos los estudiantes
- `POST /api/estudiantes/` - Crear un nuevo estudiante
- `GET /api/estudiantes/{id}/` - Obtener detalles de un estudiante
- `PUT /api/estudiantes/{id}/` - Actualizar un estudiante
- `DELETE /api/estudiantes/{id}/` - Eliminar un estudiante
- `GET /api/estudiantes/por_curso/?codigo=11-A` - Filtrar estudiantes por código de curso

### Funcionalidades adicionales

- **Búsqueda**: Usar parámetro `?search=` para buscar en todos los endpoints
- **Ordenamiento**: Usar parámetro `?ordering=campo` o `?ordering=-campo` (orden descendente)
- **Paginación**: Todas las respuestas de listado vienen paginadas con `count`, `next`, `previous` y `results`

## 📝 Licencia

Desarrollado como parte del proyecto de Arquitectura de Software 2025-1. 