# Stage 1: Build the application
FROM maven:3.8.6-eclipse-temurin-17 as builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven configuration and project files
COPY pom.xml ./
COPY src ./src

# Build the application as a WAR file
RUN mvn clean package -DskipTests

RUN mvn spring-boot:run

# Stage 2: Run the application with Tomcat 9
FROM tomcat:9.0-jdk17-corretto

# Remove the default ROOT application
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy the WAR file to Tomcat's webapps directory
COPY --from=builder /app/target/user-management-app-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
