# =========================
# BUILD STAGE
# =========================
FROM maven:3.9.16-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom first (better caching)
COPY pom.xml .

# Download dependencies (optional optimization)
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build application
RUN mvn clean package -DskipTests


# =========================
# RUNTIME STAGE
# =========================
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8081

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
