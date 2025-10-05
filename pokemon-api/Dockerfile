# Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and project files
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./
COPY src src

# Give execute permission to gradlew
RUN chmod +x ./gradlew

# Build the project
RUN ./gradlew build -x test

# Expose port Ktor runs on
EXPOSE 8080

# Start the application
CMD ["./gradlew", "run"]
