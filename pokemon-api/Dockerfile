# Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory  
WORKDIR /app

# Copy all files from the pokemon-api directory (since that's our Docker context)
COPY . .

# Give execute permission to gradlew
RUN chmod +x ./gradlew

# Expose port
EXPOSE 8080

# Start the application directly with gradlew (this works locally)
CMD ["./gradlew", "run", "--no-daemon"]
