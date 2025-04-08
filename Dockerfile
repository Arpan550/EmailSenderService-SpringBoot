FROM openjdk:17-jdk-slim

# Copy JAR into image
COPY target/EmailSenderService-0.0.1-SNAPSHOT.jar app.jar

# Start the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
