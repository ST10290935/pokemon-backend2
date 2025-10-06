# Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy gradle wrapper and build files first
COPY gradlew gradlew.bat ./
COPY gradle/ gradle/
COPY build.gradle.kts settings.gradle.kts gradle.properties ./

# Copy source code
COPY src/ src/

# Copy local.properties if it exists
COPY local.properties* ./

# Give execute permission to gradlew
RUN chmod +x ./gradlew

# Expose port
EXPOSE 8080

# Start the application directly with gradlew (this works locally)
CMD ["./gradlew", "run", "--no-daemon"]
