FROM registry.access.redhat.com/ubi8/openjdk-17-runtime:1.19-1 AS builder
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN mvn clean compile package -DskipTests

FROM eclipse-temurin:17-jre-jammy
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-Xmx128m", "-jar", "/app.jar"]