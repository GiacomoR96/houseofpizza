# Houseofpizza

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This is a Spring Boot project for data management of a pizzeria. 
The application would allow for keeping track of the orders placed by the various users, displaying the various items within the menu, management and processing of the various orders.

## Summary

- [Houseofpizza](#houseofpizza)
  - [Summary](#summary)
  - [Description](#description)
  - [Technologies Used](#technologies-used)
  - [Docker](#docker)
    - [GitHub Actions](#github-actions)
  - [Configuration, startup, and testing of the application](#configuration-startup-and-testing-of-the-application)
    - [Build application](#build-application)
    - [Startup application](#startup-application)
    - [Startup test](#startup-test)
  - [License](#license)
  - [Authors](#authors)

## Description

The application is designed to meet the management needs of a pizzeria, where it is possible to:

- Retrieve information related to the menu (with various details about ingredients, price, etc.)
- Managing a shopping cart and creating the corresponding order
- Viewing the status of previously created orders
- Process orders received from various customers
- Withdrawal and removal of orders present in the system.

The API is designed to facilitate interaction with the system, both for customers and back-office staff.


## Technologies Used

- **Spring Boot**: Main framework for application development.
- **Java 17**: Programming language used for creating and structuring the various logics of the application.
- **PostgreSQL**: Relational database for data storage.
- **Liquibase**: Manager for managing database migrations.
- **Docker**: Container for application build and image creation.
- **Spring Data JPA**: Manager for persistence of data via the database.
- **Swagger**: Tool for API documentation.
- **JavaDoc**: Used for documentation of code.
- **MapStruct**: Used for automatic mapping between objects.
- **JUnit**: Used for unit testing.


## Docker

The application has been containerized using **Docker**. The project includes a `Dockerfile` that defines how to build the application image. In addition, using **GitHub Actions**, the image is automatically built and published to a remote DockerHub repository.


### GitHub Actions

GitHub Actions are configured to automate the Continuous Integration (CI) and Continuous Delivery (CD) process.
When a change is pushed into the repository, a release pipeline is automatically initiated that includes:
1. Build and execution of unit tests.
2. Construction of the Docker image.
3. Publish produced artifact to remote repository using `DOCKERHUB_USERNAME` and `DOCKERHUB_TOKEN` secrets defined in pipeline for authentication.


## Configuration, startup and testing of the application

### Build application
```bash
git clone https://github.com/GiacomoR96/houseofpizza.git
cd houseofpizza
```

### Startup application
```bash
# Use one of the application.properties configurations on 'config-map' to be placed inside 'src/main/resources' for proper application startup
mvn clean compile install
mvn spring-boot:run
```

### Startup test
```bash
mvn test
```

## License

This project is licensed under the MIT license. See the LICENSE file for more details.


## Authors
- Giacomo Romano (GitHub reference: [github.com/GiacomoR96](https://github.com/GiacomoR96))
- Marco Raciti (GitHub reference: [github.com/Mark1096](https://github.com/Mark1096))
