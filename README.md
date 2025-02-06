
# Tu plata crece


## Overview
This is a **Spring Boot** application that provides an implementation of **Tu plata crece**.
The application is built with the following stack:

- **Spring Boot** for the backend
- **JPA/Hibernate** for ORM and database interaction
- **H2DB** for the database on memory
- **Spring Data JPA** for repository management

## Features
- Login and JWT authentication
- Loan management
- Unit tests

## Table of Contents
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Technologies](#technologies)
- [Database Configuration](#database-configuration)
- [Running the Application](#running-the-application)

## Getting Started

To get started with the application, follow these steps:

### Prerequisites
Make sure you have the following installed:
- **Java 21**
- **Maven 3.x**

### Clone the Repository

```bash
git clone https://github.com/mgrellet/tpc
cd tpc
```

### Database Configuration

On-memory DB already configured in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect

spring.jpa.hibernate.ddl-auto=create-drop
spring.sql.init.mode=always

spring.h2.console.enabled=true
spring.h2.console.path=/h2-ui
```

### Running the Application

You can run the Spring Boot application by executing:

```bash
mvn spring-boot:run
```

Alternatively, you can build the JAR file and run it:

```bash
mvn clean package
java -jar target/tuplatacrece-0.0.1-SNAPSHOT.jar
```

## API Endpoints

Below are the available API endpoints for managing users:

| HTTP Method | Endpoint              | Description                |
|-------------|-----------------------|----------------------------|
| `POST`      | `/api/auth/login`     | Login with DNI number      |
| `GET`       | `/api/loan`           | Gets the loan for the user |


### Sample API Request

#### Login

POST /api/auth/login
Content-Type: application/json
```json
{
  "dni":"31000000"
}
```

#### Response: In headers
```yaml
Authorization
Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMTAwMDAwMCIsImlhdCI6MTczODg0NjAxMiwiZXhwIjoxNzM4ODQ5NjEyfQ.RpGVpBTxyEVOKPJ5WXh7bmsVgeS2Xe3pV4LikXQxBxg
```

#### Get loan info

GET /api/loan
Content-Type: application/json
Header: Authorization
```yaml
Authorization
Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMTAwMDAwMCIsImlhdCI6MTczODg0NjAxMiwiZXhwIjoxNzM4ODQ5NjEyfQ.RpGVpBTxyEVOKPJ5WXh7bmsVgeS2Xe3pV4LikXQxBxg
```

#### Response:
```json
{
  "dni": "31000000",
  "loan": 1000000.0
}
```


## Technologies
- **Java 21**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **Hibernate**
- **H2DB**

## Running Tests

To run the unit and integration tests:

```bash
mvn test
```
