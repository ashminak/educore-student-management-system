# Student Management System

## Overview

Student Management System is a production-ready backend application built using Spring Boot. The project demonstrates enterprise-level backend development practices including JWT authentication, role-based authorization, RESTful APIs, Docker containerization, email services, PDF generation, file management, and comprehensive API documentation.

The application is designed using a layered architecture following industry best practices to ensure scalability, maintainability, and clean code organization.

---

# Features

## Authentication & Security

- JWT Authentication
- Refresh Token Authentication
- Role-Based Authorization
- Spring Security Integration
- Password Encryption using BCrypt

## User Management

- User Registration
- User Login
- User Profile Management

## Student Management

- Create Student
- Update Student
- Delete Student
- Search Students
- Pagination Support
- Student Profile

## Teacher Management

- Create Teacher
- Update Teacher
- Delete Teacher
- Search Teachers
- Teacher Profile

## Department Management

- Department CRUD Operations

## Course Management

- Course CRUD Operations
- Teacher Assignment
- Department Mapping

## Enrollment Management

- Student Course Enrollment
- Academic Year Management
- Semester Management

## Attendance Management

- Attendance Tracking
- Attendance Reports

## Marks Management

- Internal Marks
- Practical Marks
- Final Examination Marks
- Total Marks Calculation

## Dashboard

- Dashboard Statistics
- Student Count
- Teacher Count
- Department Count
- Course Count

## Notification Module

- Notification Management
- Email Notifications

## File Management

- Student Image Upload
- Teacher Image Upload
- Course Material Upload
- Document Download

## PDF Module

- Student Report Generation
- PDF Export

## Additional Features

- Global Exception Handling
- Request Validation
- DTO Mapping
- Logging
- Swagger Documentation
- Docker Support

---

# Technology Stack

## Backend

- Java 21
- Spring Boot 3
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate
- Maven

## Database

- MySQL 8

## Authentication

- JWT
- Refresh Tokens

## Documentation

- Swagger / OpenAPI 3

## Containerization

- Docker
- Docker Compose

## Utilities

- Lombok
- MapStruct

---

# Project Structure

```text
src
└── main
    ├── java
    │   └── com.student_management_system
    │       ├── AUTH
    │       ├── USER
    │       ├── STUDENT
    │       ├── TEACHER
    │       ├── COURSE
    │       ├── DEPARTMENT
    │       ├── ENROLLMENT
    │       ├── ATTENDANCE
    │       ├── MARKS
    │       ├── DASHBOARD
    │       ├── FILE
    │       ├── PDF
    │       ├── EMAIL
    │       ├── NOTIFICATION
    │       ├── config
    │       ├── security
    │       ├── exception
    │       └── util
    └── resources
```

---

# System Architecture

```
Client
   │
   ▼
Spring Security
   │
JWT Authentication
   │
Controllers
   │
Service Layer
   │
Repository Layer
   │
MySQL Database
```

---

# Prerequisites

- Java 21
- Maven 3.9+
- MySQL 8
- Docker Desktop (Optional)

---

# Running the Project

## Clone Repository

```bash
git clone https://github.com/ashminak/student-management-system.git
```

## Navigate

```bash
cd student-management-system
```

## Build

```bash
mvn clean install
```

## Run

```bash
mvn spring-boot:run
```

---

# Running with Docker

## Build

```bash
docker compose build
```

## Start Containers

```bash
docker compose up -d
```

## Stop Containers

```bash
docker compose down
```

---

# Configuration

Configure the following properties inside `application.properties`.

```
spring.datasource.url
spring.datasource.username
spring.datasource.password

jwt.secret

spring.mail.username
spring.mail.password
```

---

# API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI Documentation

```
http://localhost:8080/v3/api-docs
```

---

# Authentication Flow

1. Register a user.
2. Login using credentials.
3. Receive JWT Access Token.
4. Authorize using:

```
Bearer <access_token>
```

5. Access secured APIs.

---

# REST API Modules

| Module | Status |
|---------|--------|
| Authentication | Completed |
| User | Completed |
| Student | Completed |
| Teacher | Completed |
| Department | Completed |
| Course | Completed |
| Enrollment | Completed |
| Attendance | Completed |
| Marks | Completed |
| Dashboard | Completed |
| File Upload | Completed |
| Email | Completed |
| PDF Generation | Completed |

---

# Testing

The APIs can be tested using:

- Swagger UI
- Postman

---

# Future Improvements

- React Frontend
- Unit Testing using JUnit 5
- Mockito Integration
- Redis Caching
- GitHub Actions CI/CD
- AWS Deployment
- Kubernetes
- RabbitMQ / Kafka
- Monitoring using Prometheus and Grafana

---

# Docker Support

The project includes:

- Dockerfile
- Docker Compose
- Docker Ignore

Run the complete application using:

```bash
docker compose up
```

---

# Author

**Ashmina Khatun**

Java Backend Developer

GitHub: https://github.com/ashminak

LinkedIn:https://www.linkedin.com/in/ashmina-khatun-67a3601b4/

---

# License

This project is intended for educational and portfolio purposes.
#   e d u c o r e - s t u d e n t - m a n a g e m e n t - s y s t e m 
 
 
