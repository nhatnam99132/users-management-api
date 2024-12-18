# Stage 1: Build the application
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven Wrapper files
COPY .mvn/ .mvn
COPY mvnw .
COPY mvnw.cmd .

# Convert line endings
RUN apt-get update && apt-get install -y dos2unix && dos2unix mvnw

# Ensure mvnw has executable permissions
RUN chmod +x mvnw

# Copy the Maven build files
COPY pom.xml .
COPY src ./src

# Build the application
RUN ./mvnw package -DskipTests



# Stage 2: Create the production image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built application from the previous stage
COPY --from=build /app/target/user-management-app-0.0.1-SNAPSHOT.war /app/user-management-app.war

# Set environment variables
ARG JDBC_DATABASE_URL
ARG JDBC_DATABASE_USERNAME
ARG JDBC_DATABASE_PASSWORD
ENV JDBC_DATABASE_URL=$JDBC_DATABASE_URL
ENV JDBC_DATABASE_USERNAME=$JDBC_DATABASE_USERNAME
ENV JDBC_DATABASE_PASSWORD=$JDBC_DATABASE_PASSWORD

# Expose the port the app runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/user-management-app.war"]
