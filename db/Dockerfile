FROM postgres:13

# Add the initialization script
COPY init-user-db.sh /docker-entrypoint-initdb.d/init-user-db.sh

# Ensure the script has execution permissions
RUN chmod +x /docker-entrypoint-initdb.d/init-user-db.sh
