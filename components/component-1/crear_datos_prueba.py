import os
import django
import datetime
import random
import logging

# Configurar logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

# Configurar entorno Django
os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'sia_colegios.settings')
django.setup()

# Importar modelos después de configurar el entorno
from gestion_estudiantes.models import Curso, Estudiante

def limpiar_datos():
    """Limpia todos los datos existentes"""
    logger.info("Limpiando datos existentes...")
    Estudiante.objects.all().delete()
    Curso.objects.all().delete()
    logger.info("Datos limpiados exitosamente.")

def crear_cursos():
    """Crear cursos de ejemplo"""
    cursos_data = [
        {'nombre': 'Primero A', 'codigo': '1-A'},
        {'nombre': 'Primero B', 'codigo': '1-B'},
        {'nombre': 'Primero C', 'codigo': '1-C'},
        {'nombre': 'Segundo A', 'codigo': '2-A'},
        {'nombre': 'Segundo B', 'codigo': '2-B'},
        {'nombre': 'Segundo C', 'codigo': '2-C'},
        {'nombre': 'Tercero A', 'codigo': '3-A'},
        {'nombre': 'Tercero B', 'codigo': '3-B'},
        {'nombre': 'Tercero C', 'codigo': '3-C'},
        {'nombre': 'Cuarto A', 'codigo': '4-A'},
        {'nombre': 'Quinto A', 'codigo': '5-A'},
        {'nombre': 'Sexto A', 'codigo': '6-A'},
        {'nombre': 'Séptimo A', 'codigo': '7-A'},
        {'nombre': 'Octavo A', 'codigo': '8-A'},
        {'nombre': 'Noveno A', 'codigo': '9-A'},
        {'nombre': 'Décimo A', 'codigo': '10-A'},
        {'nombre': 'Once A', 'codigo': '11-A'},
        {'nombre': 'Once B', 'codigo': '11-B'},
    ]
    
    cursos_creados = []
    for curso_data in cursos_data:
        try:
            curso = Curso.objects.create(**curso_data)
            logger.info(f"Curso creado: {curso.codigo} - {curso.nombre}")
            cursos_creados.append(curso)
        except Exception as e:
            logger.error(f"Error al crear curso {curso_data['codigo']}: {e}")
    
    return cursos_creados

def crear_estudiantes(cursos):
    """Crear estudiantes de ejemplo"""
    if not cursos:
        logger.error("No hay cursos disponibles para crear estudiantes")
        return
    
    # Nombres y apellidos para generar combinaciones aleatorias
    nombres = [
        'Ana', 'Juan', 'Sofía', 'Miguel', 'Laura', 'Carlos', 'Valentina', 'Andrés', 
        'María', 'José', 'Isabella', 'Santiago', 'Camila', 'Daniel', 'Gabriela', 'Alejandro',
        'Valeria', 'Mateo', 'Lucía', 'Samuel', 'Mariana', 'Nicolás', 'Paula', 'David'
    ]
    
    apellidos = [
        'García', 'Rodríguez', 'Martínez', 'López', 'González', 'Pérez', 'Sánchez', 'Ramírez',
        'Torres', 'Flores', 'Rivera', 'Gómez', 'Díaz', 'Reyes', 'Cruz', 'Morales',
        'Herrera', 'Ortiz', 'Vargas', 'Mendoza', 'Castillo', 'Rojas', 'Romero', 'Álvarez'
    ]
    
    # Estudiantes existentes
    estudiantes_data = [
        {
            'nombre_completo': 'Ana María López',
            'documento': '1001234567',
            'fecha_nacimiento': datetime.date(2010, 5, 15),
            'acudiente': 'María Rodríguez',
            'curso': cursos[0]  # Primero A
        },
        {
            'nombre_completo': 'Juan Carlos Pérez',
            'documento': '1001234568',
            'fecha_nacimiento': datetime.date(2009, 8, 21),
            'acudiente': 'Carlos Pérez',
            'curso': cursos[4]  # Segundo B
        },
        {
            'nombre_completo': 'Sofía Alejandra Martínez',
            'documento': '1001234569',
            'fecha_nacimiento': datetime.date(2008, 3, 10),
            'acudiente': 'Alejandra Martínez',
            'curso': cursos[8]  # Tercero C
        },
        {
            'nombre_completo': 'Miguel Ángel Torres',
            'documento': '1001234570',
            'fecha_nacimiento': datetime.date(2002, 9, 5),
            'acudiente': 'Ángela Torres',
            'curso': cursos[14]  # Noveno A
        },
        {
            'nombre_completo': 'Laura Valentina Gómez',
            'documento': '1001234571',
            'fecha_nacimiento': datetime.date(2000, 2, 14),
            'acudiente': 'Valentina Gómez',
            'curso': cursos[16]  # Once A
        },
        {
            'nombre_completo': 'Carlos Eduardo Sánchez',
            'documento': '1001234572',
            'fecha_nacimiento': datetime.date(2000, 7, 30),
            'acudiente': 'Eduardo Sánchez',
            'curso': cursos[16]  # Once A
        }
    ]
    
    # Generar 30 estudiantes adicionales aleatoriamente
    for i in range(1, 31):
        nombre = random.choice(nombres)
        apellido1 = random.choice(apellidos)
        apellido2 = random.choice(apellidos)
        nombre_completo = f"{nombre} {apellido1} {apellido2}"
        
        # Generar documento único
        documento = f"10012345{72 + i}"
        
        # Fecha nacimiento aleatoria entre 2000 y 2015
        year = random.randint(2000, 2015)
        month = random.randint(1, 12)
        day = random.randint(1, 28)  # Limitado a 28 para evitar problemas con febrero
        fecha_nacimiento = datetime.date(year, month, day)
        
        # Asignar acudiente
        acudiente = f"{random.choice(nombres)} {random.choice(apellidos)}"
        
        # Asignar curso según la edad
        edad = 2025 - year
        if edad <= 7:
            curso_index = random.randint(0, 2)  # Primero
        elif edad <= 8:
            curso_index = random.randint(3, 5)  # Segundo
        elif edad <= 9:
            curso_index = random.randint(6, 8)  # Tercero
        elif edad <= 10:
            curso_index = 9  # Cuarto
        elif edad <= 11:
            curso_index = 10  # Quinto
        elif edad <= 12:
            curso_index = 11  # Sexto
        elif edad <= 13:
            curso_index = 12  # Séptimo
        elif edad <= 14:
            curso_index = 13  # Octavo
        elif edad <= 15:
            curso_index = 14  # Noveno
        elif edad <= 16:
            curso_index = 15  # Décimo
        else:
            curso_index = random.choice([16, 17])  # Once
            
        curso = cursos[min(curso_index, len(cursos) - 1)]  # Evitar índice fuera de rango
        
        estudiantes_data.append({
            'nombre_completo': nombre_completo,
            'documento': documento,
            'fecha_nacimiento': fecha_nacimiento,
            'acudiente': acudiente,
            'curso': curso
        })
    
    for estudiante_data in estudiantes_data:
        try:
            estudiante = Estudiante.objects.create(**estudiante_data)
            logger.info(f"Estudiante creado: {estudiante.nombre_completo} - Curso: {estudiante.curso.codigo}")
        except Exception as e:
            logger.error(f"Error al crear estudiante {estudiante_data['nombre_completo']}: {e}")

def estadisticas():
    """Mostrar estadísticas de los datos cargados"""
    total_cursos = Curso.objects.count()
    total_estudiantes = Estudiante.objects.count()
    
    logger.info("\n--- ESTADÍSTICAS DE DATOS ---")
    logger.info(f"Total de cursos: {total_cursos}")
    logger.info(f"Total de estudiantes: {total_estudiantes}")
    logger.info("\nEstudiantes por curso:")
    
    for curso in Curso.objects.all().order_by('codigo'):
        num_estudiantes = Estudiante.objects.filter(curso=curso).count()
        logger.info(f"  {curso.codigo} - {curso.nombre}: {num_estudiantes} estudiantes")

def main():
    """Función principal que ejecuta todo el proceso"""
    try:
        logger.info("Iniciando creación de datos de prueba para el SIA Colegios...")
        
        # Limpiar datos existentes
        limpiar_datos()
        
        # Crear nuevos datos
        cursos = crear_cursos()
        crear_estudiantes(cursos)
        
        # Mostrar estadísticas
        estadisticas()
        
        logger.info("Proceso completado exitosamente.")
        return True
    except Exception as e:
        logger.error(f"Error durante la creación de datos: {e}")
        return False

if __name__ == '__main__':
    main() 