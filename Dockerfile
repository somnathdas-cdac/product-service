# Stage 1: Build the Maven application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application skipping test suites for faster deployment
RUN mvn clean package -DskipTests

# Stage 2: Runtime environment
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8081

# Run the spring boot app
ENTRYPOINT ["java", "-jar", "app.jar"]

