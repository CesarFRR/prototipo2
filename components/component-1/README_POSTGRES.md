# Configuración de PostgreSQL para SIA Colegios en Windows

Este documento describe el proceso de configuración de PostgreSQL para el proyecto SIA Colegios en entornos Windows, incluyendo la solución a problemas comunes.

## Requisitos Previos

- PostgreSQL 17 instalado en Windows
- Python 3.13 o superior
- Django 5.2.1
- DBeaver (opcional, para gestión visual de la base de datos)

## Configuración Inicial de Django con PostgreSQL

1. Configura el archivo `settings.py` para usar PostgreSQL:

```python
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.postgresql_psycopg2',
        'NAME': 'sia_colegios',
        'USER': 'postgres',
        'PASSWORD': 'postgres',  # Cambia esto a tu contraseña real
        'HOST': 'localhost',
        'PORT': '5432',
        'OPTIONS': {
            'client_encoding': 'UTF8',
        },
    }
}
```

2. Instala los paquetes necesarios:

```bash
pip install psycopg2-binary
pip install django-cors-headers
```

## Problemas Comunes y Soluciones

### Problema 1: Error de Decodificación Unicode

**Error:**
```
UnicodeDecodeError: 'utf-8' codec can't decode byte 0xf3 in position 85: invalid continuation byte
```

**Solución:**
1. Si es posible, usa un string de conexión directa:

```python
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.postgresql_psycopg2',
        'OPTIONS': {
            'dsn': 'dbname=sia_colegios user=postgres password=postgres host=localhost port=5432',
        },
    }
}
```

2. O cambia temporalmente a SQLite para desarrollo:

```python
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': BASE_DIR / 'db.sqlite3',
    }
}
```

### Problema 2: Error de Autenticación en PostgreSQL

**Error:**
```
FATAL: la autentificación password falló para el usuario «postgres»
```

**Solución:**
1. Verifica la contraseña que estás usando
2. Modifica el archivo de configuración de PostgreSQL para permitir cambiar la contraseña:

   a. Localiza el archivo pg_hba.conf:
   ```
   C:\Program Files\PostgreSQL\17\data\pg_hba.conf
   ```

   b. Edita las líneas de configuración para cambiar temporalmente los métodos de autenticación:
   ```
   # IPv4 local connections:
   host    all    all    127.0.0.1/32    trust
   # IPv6 local connections:
   host    all    all    ::1/128         trust
   ```

   c. Reinicia el servicio de PostgreSQL
   
   d. Cambia la contraseña:
   ```
   set-location "C:\Program Files\PostgreSQL\17\bin"
   .\psql -U postgres -c "ALTER USER postgres WITH PASSWORD 'postgres';"
   ```

   e. Restaura la configuración original en pg_hba.conf (cambia trust por scram-sha-256 o md5)
   
   f. Reinicia el servicio de PostgreSQL nuevamente

### Problema 3: La Base de Datos No Existe

**Error:**
```
FATAL: no existe la base de datos «sia_colegios»
```

**Solución:**
Crea la base de datos con el comando correcto:

```
set-location "C:\Program Files\PostgreSQL\17\bin"
.\psql -U postgres -c "CREATE DATABASE sia_colegios;"
```

Si hay problemas con el "collation" o codificación regional:

```
.\psql -U postgres -c "CREATE DATABASE sia_colegios WITH TEMPLATE template0 ENCODING 'UTF8';"
```

O especificando la configuración regional correcta:

```
.\psql -U postgres -c "CREATE DATABASE sia_colegios WITH ENCODING 'UTF8' LC_COLLATE 'Spanish_Colombia.1252' LC_CTYPE 'Spanish_Colombia.1252';"
```

## Configuración de DBeaver

Para conectar DBeaver a la base de datos PostgreSQL:

1. Crea una nueva conexión PostgreSQL en DBeaver
2. Configura los parámetros:
   - Host: localhost
   - Puerto: 5432
   - Base de datos: sia_colegios
   - Usuario: postgres
   - Contraseña: postgres (o la que hayas configurado)

3. Si hay problemas con caracteres especiales, ve a la pestaña "Driver Properties":
   - Busca la propiedad "charSet"
   - Establece su valor a: UTF-8

## Verificación de la Configuración

Para verificar que la aplicación esté usando PostgreSQL:

1. Ejecuta las migraciones:
```
python manage.py migrate
```

2. Carga datos de prueba:
```
python crear_datos_prueba.py
```

3. Inicia el servidor:
```
python manage.py runserver
```

4. Verifica en DBeaver que puedes ver las tablas y datos creados

## Comandos Útiles

- Mostrar bases de datos existentes:
```
.\psql -U postgres -c "SELECT datname FROM pg_database;"
```

- Mostrar tablas en la base de datos:
```
.\psql -U postgres -d sia_colegios -c "\dt"
```

- Verificar la configuración de codificación:
```
.\psql -U postgres -d sia_colegios -c "SHOW server_encoding;"
```

## Solución de Problemas Adicionales

### Problemas con PowerShell

Si experimentas problemas ejecutando comandos en PowerShell:

1. Usa CMD en lugar de PowerShell
2. O ejecuta PowerShell como administrador
3. Evita comandos multilínea

### Problemas con permisos

Si no puedes editar archivos de configuración debido a permisos:

1. Abre el editor como administrador
2. Copia el archivo a una ubicación temporal, edítalo y luego cópialo de vuelta

### Problemas con caracteres especiales

Si sigues teniendo problemas con caracteres especiales:

1. Usa otro cliente como pgAdmin 4 (incluido con PostgreSQL)
2. O configura las variables de entorno LANG y LC_ALL en Windows 