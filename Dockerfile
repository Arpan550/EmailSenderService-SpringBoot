# Use a lightweight Java image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory
WORKDIR /app

# Copy the built jar file into the container
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
