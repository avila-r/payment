# Stage 1: Build Stage
FROM gradle:jdk21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle files and source code
COPY build.gradle.kts settings.gradle.kts gradle.properties /app/
COPY gradle /app/gradle
COPY src /app/src

# Copy Gradle wrapper scripts
COPY gradlew gradlew.bat /app/

# Build the application using Gradle
RUN ./gradlew bootJar

# Stage 2: Production Stage
FROM openjdk:21

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expose port 8000 for the application
EXPOSE 8000

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]