#!/bin/bash
set -e

# Función para esperar a que PostgreSQL esté disponible
wait_for_postgres() {
  echo "Esperando a que PostgreSQL esté disponible..."
  while ! pg_isready -h $DB_HOST -p $DB_PORT -U $DB_USER > /dev/null 2>&1; do
    echo "PostgreSQL no disponible, esperando..."
    sleep 1
  done
  echo "PostgreSQL disponible. Continuando."
}

# Función para verificar si ya existen datos
check_data_exists() {
  echo "Verificando si ya existen datos..."
  if python -c "import os; os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'sia_colegios.settings'); import django; django.setup(); from gestion_estudiantes.models import Curso; print(Curso.objects.count())" | grep -q "^0$"; then
    echo "No hay datos en la base de datos."
    return 1  # No hay datos
  else
    echo "Ya existen datos en la base de datos."
    return 0  # Hay datos
  fi
}

# Función para cargar datos iniciales
load_initial_data() {
  echo "Cargando datos iniciales..."
  python crear_datos_prueba.py
  if [ $? -eq 0 ]; then
    echo "Datos cargados exitosamente."
    return 0
  else
    echo "Error al cargar datos iniciales."
    return 1
  fi
}

# Ejecutar migraciones y crear datos iniciales solo si se especifica
if [ "$1" = "django" ]; then
  wait_for_postgres
  
  echo "Aplicando migraciones..."
  python manage.py migrate --noinput
  
  if [ "$LOAD_INITIAL_DATA" = "true" ]; then
    if ! check_data_exists; then
      load_initial_data
    else
      echo "Ya existen datos en la base de datos. Omitiendo carga inicial."
    fi
  fi
  
  echo "Iniciando servidor Django..."
  exec python manage.py runserver 0.0.0.0:8000
  
elif [ "$1" = "gunicorn" ]; then
  wait_for_postgres
  
  echo "Aplicando migraciones..."
  python manage.py migrate --noinput
  
  if [ "$LOAD_INITIAL_DATA" = "true" ]; then
    if ! check_data_exists; then
      load_initial_data
    else
      echo "Ya existen datos en la base de datos. Omitiendo carga inicial."
    fi
  fi
  
  echo "Iniciando Gunicorn..."
  exec gunicorn sia_colegios.wsgi:application --bind 0.0.0.0:8000
  
else
  exec "$@"
fi 