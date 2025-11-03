# Dockerfile
FROM openjdk:17-slim
WORKDIR /app
COPY revagenda-server/target/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]