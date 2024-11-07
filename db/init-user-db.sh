#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "postgres" <<-EOSQL
    SELECT 'CREATE DATABASE user_management'
    WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'user_management')\\gexec
EOSQL
