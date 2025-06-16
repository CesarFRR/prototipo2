from django.shortcuts import render, get_object_or_404
from rest_framework import viewsets, filters, status
from rest_framework.decorators import action
from rest_framework.response import Response
from django.db.models.deletion import ProtectedError
from .models import Curso, Estudiante
from .serializers import CursoSerializer, EstudianteSerializer, EstudianteDetailSerializer

class CursoViewSet(viewsets.ModelViewSet):
    """
    API endpoint para gestionar los cursos/grados escolares
    """
    queryset = Curso.objects.all()
    serializer_class = CursoSerializer
    filter_backends = [filters.SearchFilter, filters.OrderingFilter]
    search_fields = ['nombre', 'codigo']
    ordering_fields = ['nombre', 'codigo']
    
    @action(detail=True, methods=['get'])
    def estudiantes(self, request, pk=None):
        """
        Obtener todos los estudiantes de un curso específico
        """
        curso = self.get_object()
        estudiantes = Estudiante.objects.filter(curso=curso)
        serializer = EstudianteSerializer(estudiantes, many=True)
        return Response(serializer.data)

    def destroy(self, request, *args, **kwargs):
        instance = self.get_object()
        try:
            self.perform_destroy(instance)
        except ProtectedError:
            return Response(
                {"detail": "No se puede eliminar el curso porque tiene estudiantes asociados."},
                status=status.HTTP_409_CONFLICT
            )
        return Response(status=status.HTTP_204_NO_CONTENT)

class EstudianteViewSet(viewsets.ModelViewSet):
    """
    API endpoint para gestionar estudiantes
    """
    queryset = Estudiante.objects.all()
    serializer_class = EstudianteSerializer
    filter_backends = [filters.SearchFilter, filters.OrderingFilter]
    search_fields = ['nombre_completo', 'documento', 'acudiente']
    ordering_fields = ['nombre_completo', 'fecha_nacimiento', 'fecha_registro']
    
    def get_serializer_class(self):
        if self.action == 'retrieve':
            return EstudianteDetailSerializer
        return EstudianteSerializer
    
    @action(detail=False, methods=['get'])
    def por_curso(self, request):
        """
        Filtrar estudiantes por código de curso
        """
        codigo_curso = request.query_params.get('codigo', None)
        if codigo_curso:
            curso = get_object_or_404(Curso, codigo=codigo_curso)
            estudiantes = Estudiante.objects.filter(curso=curso)
            serializer = self.get_serializer(estudiantes, many=True)
            return Response(serializer.data)
        return Response({"error": "Debe proporcionar el código del curso"}, status=400)

    def destroy(self, request, *args, **kwargs):
        instance = self.get_object()
        self.perform_destroy(instance)
        return Response(
            {"detail": "Estudiante eliminado correctamente."},
            status=status.HTTP_200_OK
        )
