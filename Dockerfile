# Etapa 1: Build de la app
FROM gradle:8.5.0-jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
