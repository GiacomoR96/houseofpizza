FROM maven:3.8.5-openjdk-17 AS builder
RUN ls -la
RUN mvn clean compile package -DskipTests

FROM eclipse-temurin:17-jre-jammy
COPY --from=builder /target/*.jar app.jar
ENTRYPOINT ["java", "-Xmx128m", "-jar", "/app.jar"]