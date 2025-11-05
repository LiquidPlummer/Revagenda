# Dockerfile
# FROM openjdk:17-slim # Deprecated as of 11/3
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY revagenda-server/target/*.jar app.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app.jar"]