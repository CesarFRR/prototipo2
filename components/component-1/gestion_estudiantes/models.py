from django.db import models
from django.core.exceptions import ValidationError
from django.utils import timezone

# Create your models here.

class Curso(models.Model):
    """Modelo para representar un grado escolar"""
    nombre = models.CharField(max_length=50)
    codigo = models.CharField(max_length=10, unique=True)  # Ejemplo: "11-A"
    
    def __str__(self):
        return self.codigo

    class Meta:
        ordering = ['codigo']

class Estudiante(models.Model):
    """Modelo para representar un estudiante"""
    nombre_completo = models.CharField(max_length=255)
    documento = models.CharField(max_length=20, unique=True)
    fecha_nacimiento = models.DateField()
    acudiente = models.CharField(max_length=255)
    curso = models.ForeignKey(Curso, related_name='estudiantes', on_delete=models.PROTECT)
    fecha_registro = models.DateTimeField(auto_now_add=True)
    
    def __str__(self):
        return f"{self.nombre_completo} - {self.documento}"
    
    def clean(self):
        # Validar que la fecha de nacimiento no sea en el futuro
        if self.fecha_nacimiento and self.fecha_nacimiento > timezone.now().date():
            raise ValidationError({'fecha_nacimiento': 'La fecha de nacimiento no puede ser en el futuro.'})
        
        # Validar que el documento solo contenga números
        if not self.documento.isdigit():
            raise ValidationError({'documento': 'El documento debe contener solo números.'})
    
    def save(self, *args, **kwargs):
        self.full_clean()
        super().save(*args, **kwargs)
    
    class Meta:
        ordering = ['nombre_completo']
