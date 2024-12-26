FROM maven:3.8.5-openjdk-17 AS builder
COPY . /app
WORKDIR /app
COPY /config-map/prod/application.properties /app/src/main/resources/application.properties
RUN mvn clean compile package -DskipTests
#RUN FILES=$(ls -1 /app/src/main/resources) && echo "FILES=$FILES" >> env.txt

FROM eclipse-temurin:17-jre-jammy
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-Xmx128m", "-jar", "/app.jar"]