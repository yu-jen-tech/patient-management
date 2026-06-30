# --- STAGE 1: BUILDER ---
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
# Copy pom first to cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B
# Copy source code and build the application
COPY src ./src
RUN mvn clean package

# --- STAGE 2: RUNNER ---
#FROM openjdk:21-jdk AS runner
FROM eclipse-temurin:21-jdk AS runner
WORKDIR /app
# Copy ONLY the built jar file from the builder stage
COPY --from=builder /app/target/api-gateway-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 4004
ENTRYPOINT ["java", "-jar", "/app/app.jar"]