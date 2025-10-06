# Use OpenJDK 17
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the entire pokemon-api directory
COPY pokemon-api/ ./

# Make gradlew executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew buildFatJar --no-daemon

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "build/libs/pokemon-api-all.jar"]