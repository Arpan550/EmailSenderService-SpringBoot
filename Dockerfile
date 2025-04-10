# Stage 1: Build with JDK 24
FROM eclipse-temurin:24-jdk-jammy AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Stage 2: Run with JDK 24
FROM eclipse-temurin:24-jdk
WORKDIR /app
COPY --from=build /app/target/medichain-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
