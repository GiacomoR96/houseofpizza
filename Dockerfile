FROM openjdk:8-jre
ADD target/houseofpizza-0.0.2-SNAPSHOT.jar app.jar
ADD house-of-pizza-be.yml app-config.yml
EXPOSE 8020
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar", "server", "app-config.yml"]