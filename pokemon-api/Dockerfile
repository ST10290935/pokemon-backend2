# Use an official OpenJDK image for building
FROM openjdk:17-jdk-slim AS builder

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and project files
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY src src

# Copy additional files that might be needed
COPY local.properties ./

# Give execute permission to gradlew
RUN chmod +x ./gradlew

# Build the project and create fat JAR
RUN ./gradlew buildFatJar -x test --no-daemon

# Use a smaller runtime image
FROM openjdk:17-jre-slim

# Set working directory
WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/build/libs/*-all.jar app.jar

# Expose the port (Render will override with PORT env var)
EXPOSE $PORT

# Start the application with proper Java options for containers
CMD ["java", "-server", "-XX:+UseContainerSupport", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]
