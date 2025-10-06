# Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory  
WORKDIR /app

# Copy all files from the pokemon-api directory (since that's our Docker context)
COPY . .

# Expose port
EXPOSE 8080

# Start the application using sh to execute gradlew (avoids chmod issues)
CMD ["sh", "./gradlew", "run", "--no-daemon"]
