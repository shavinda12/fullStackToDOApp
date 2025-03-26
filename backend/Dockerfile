# Build Stage: Runs Tests First
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Run Tests First
RUN mvn test

# If tests pass, build the application
RUN mvn clean package -DskipTests

# Runtime Stage: Run the built JAR
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose Application Port
EXPOSE 4000

# Run the Application
ENTRYPOINT ["java", "-jar", "app.jar"]