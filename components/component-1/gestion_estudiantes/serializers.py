from rest_framework import serializers
from .models import Curso, Estudiante

class CursoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Curso
        fields = '__all__'

class EstudianteSerializer(serializers.ModelSerializer):
    class Meta:
        model = Estudiante
        fields = '__all__'
    
    def validate_documento(self, value):
        if not value.isdigit():
            raise serializers.ValidationError("El documento debe contener solo números.")
        return value
    
    def validate_fecha_nacimiento(self, value):
        from django.utils import timezone
        if value > timezone.now().date():
            raise serializers.ValidationError("La fecha de nacimiento no puede ser en el futuro.")
        return value
    
    def validate(self, data):
        # Validar que el curso exista
        curso = data.get('curso')
        if curso and not Curso.objects.filter(id=curso.id).exists():
            raise serializers.ValidationError({"curso": "El curso especificado no existe."})
        return data

class EstudianteDetailSerializer(serializers.ModelSerializer):
    curso = CursoSerializer(read_only=True)
    
    class Meta:
        model = Estudiante
        fields = '__all__' 