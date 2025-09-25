# syntax=docker/dockerfile:1

FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY informationSystemDesign /app
WORKDIR /app
RUN ./gradlew build --no-daemon

# Stage 2: Runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
CMD ["java", "-jar", "/app/app.jar"]
