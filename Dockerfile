# Use OpenJDK 23 as base image
FROM openjdk:23-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first for better layer caching
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src/ src/

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8686

# Run the application
CMD ["java", "-jar", "target/puttingItTogether-0.0.1-SNAPSHOT.jar"]