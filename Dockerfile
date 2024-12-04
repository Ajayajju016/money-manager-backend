# Use Maven as the build stage
FROM maven:3.9.0-eclipse-temurin-17 as build

# Set the working directory
WORKDIR /build

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application (without running tests)
RUN mvn clean package -DskipTests

# Use OpenJDK as the runtime stage
FROM openjdk:17-jdk-slim

# Set the working directory for the runtime
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /build/target/manager-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 for the application
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]