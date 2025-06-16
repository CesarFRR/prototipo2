#!/bin/bash
set -e

# Ejecutar comandos como usuario postgres
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    -- Configurar el esquema public
    CREATE SCHEMA IF NOT EXISTS public;
    GRANT ALL ON SCHEMA public TO postgres;
    GRANT ALL ON SCHEMA public TO public;

    -- Configurar extensiones adicionales si son necesarias
    -- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
    -- CREATE EXTENSION IF NOT EXISTS "pg_trgm";

    -- Configurar codificación y collation para la base de datos
    ALTER DATABASE "$POSTGRES_DB" SET client_encoding TO 'UTF8';
EOSQL

echo "Base de datos PostgreSQL inicializada correctamente." 