FROM python:3.9-bullseye

# Configurar variables de entorno
ENV PYTHONDONTWRITEBYTECODE=1
ENV PYTHONUNBUFFERED=1
ENV LANG es_CO.UTF-8
ENV LC_ALL es_CO.UTF-8

# Establecer directorio de trabajo
WORKDIR /app

# Instalar dependencias del sistema
RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        postgresql-client \
        libpq-dev \
        gcc \
        gettext \
        build-essential \
        python3-dev \
        libc6-dev \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Instalar dependencias Python
COPY requirements.txt .
RUN pip install --upgrade pip \
    && pip install -r requirements.txt

# Copiar el proyecto
COPY . .

# Script de entrada para esperar a que PostgreSQL esté disponible
COPY docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh

# Puerto por defecto para Django
EXPOSE 8000

# Usar el script de entrada
ENTRYPOINT ["/docker-entrypoint.sh"] 