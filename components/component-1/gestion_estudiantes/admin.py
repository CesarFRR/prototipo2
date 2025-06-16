from django.contrib import admin
from .models import Curso, Estudiante

@admin.register(Curso)
class CursoAdmin(admin.ModelAdmin):
    list_display = ('codigo', 'nombre')
    search_fields = ('codigo', 'nombre')
    ordering = ('codigo',)

@admin.register(Estudiante)
class EstudianteAdmin(admin.ModelAdmin):
    list_display = ('nombre_completo', 'documento', 'curso', 'fecha_nacimiento', 'acudiente')
    list_filter = ('curso',)
    search_fields = ('nombre_completo', 'documento', 'acudiente')
    ordering = ('nombre_completo',)
