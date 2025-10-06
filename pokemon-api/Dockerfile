# Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory  
WORKDIR /app

# Copy all files from the pokemon-api directory (since that's our Docker context)
COPY . .

# Make gradlew executable and verify files are present
RUN ls -la && chmod +x gradlew && ls -la gradlew

# Expose port
EXPOSE 8080

# Start the application directly with gradlew
CMD ["./gradlew", "run", "--no-daemon"]
