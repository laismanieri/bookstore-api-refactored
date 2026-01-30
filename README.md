<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.10-brightgreen"/>
  <img src="https://img.shields.io/badge/Status-In%20Progress-blueviolet"/>
  <a href="https://github.com/laismanieri">
    <img alt="Made by Lais Manieri" src="https://img.shields.io/badge/Made%20by-LaisManieri-yellow">
  </a>
</p>

# <h1 align="center">Bookstore API Evolution<br><sub>Backend Refactoring Journey (2023 → 2026)</sub></h1>

## 📖 Overview

<p align="justify">
The **Bookstore API** is a Spring Boot REST API that demonstrates the evolution from a basic CRUD application to a production-ready backend following industry best practices. Originally developed as an academic project during the SENAI Technical Course (2023), this codebase has been extensively refactored to showcase enterprise-level Java development skills.

### Key Highlights

- ✅ **Clean Architecture** with proper separation of concerns (Controller → Service → Repository)
- ✅ **DTO Pattern** to decouple domain entities from API contracts
- ✅ **Bean Validation** with comprehensive input validation (`@Valid`, `@NotBlank`, `@Positive`)
- ✅ **Business Rules** enforcement (e.g., unique book types per book, automatic discount calculation)
- ✅ **Multi-environment support** via Spring Profiles (H2 for dev, MySQL for prod)
- ✅ **Global Exception Handling** with `@ControllerAdvice`
- ✅ **GUID-based public identifiers** instead of exposing database IDs
- ✅ **Bidirectional JSON serialization** handling to prevent infinite recursion

---

## 🚀 Features

### Core Functionality
- Full CRUD operations for **Books** and **BookDetails** entities
- Support for multiple book formats per title (physical, ebook, audiobook)
- Automatic discounted price calculation (10% off when `onSale = true`)
- Stock quantity management with validation
- Business rule: One book type (format) per book maximum

### Technical Features
- RESTful API design with semantic HTTP status codes
- Request/Response DTOs with Bean Validation
- JPA relationships (`@OneToMany` / `@ManyToOne`) with cascade configuration
- Centralized logging with SLF4J
- H2 in-memory database for local development
- MySQL support for production-like environments
- Postman collection included for testing

---
## 🏗 Architecture

```plaintext
bookstore-api/
├── controller/      # REST endpoints
├── service/         # Business logic
├── repository/      # Data access
├── mapper/          # DTO conversion
├── dto/             # Request/Response DTOs
├── entity/          # JPA entities
├── validator/       # Custom validators
├── exception/       # Error handling
└── config/          # Configuration
```

---

## 🛠 Tech Stack

| Category | Technologies |
|----------|-------------|
| **Language** | Java 21 |
| **Framework** | Spring Boot 3.5.10 |
| **Build Tool** | Maven |
| **ORM** | Spring Data JPA / Hibernate |
| **Validation** | Jakarta Bean Validation |
| **Database** | H2 (dev), MySQL 8+ (prod) |
| **Logging** | SLF4J + Logback |
| **Utilities** | Lombok, Jackson |
| **Testing** | Spring Boot Test, JUnit 5 |

---

## 🛠️ TOOLS
- Postman (API testing)
- H2 Console (data visualization)
- Git / GitHub

---

## 📋 Prerequisites

Before running the application, ensure you have:

- **Java 21** or higher ([Download](https://openjdk.org/projects/jdk/21/))
- **Maven 3.8+** ([Download](https://maven.apache.org/download.cgi))
- **MySQL 8+** (optional, for production profile)
- **Git** for cloning the repository

---

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/laismanieri/bookstore-api-refactored.git
cd bookstore-api-refactored
```

### 2. Configure Database (Optional for Production)
Edit src/main/resources/application-prod.yml:

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/bookstore_db
    username: your_username
    password: your_password
```

### 3. Run the Application
Development Mode (H2 in-memory):
```bash
mvn spring-boot:run
```
Production Profile (MySQL):
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### 4. Access the API
Base URL: http://localhost:8080/v1/api

- H2 Console (dev only): http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:bookstore
- Username: sa
 - Password: (empty)

 ## 📚 API Documentation
Books Endpoints
| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/v1/api/books` | Create a new book | No |
| `GET` | `/v1/api/books` | List all books | No |
| `GET` | `/v1/api/books/{guid}` | Get book by GUID | No |
| `PUT` | `/v1/api/books/{guid}` | Update book | No |
| `DELETE` | `/v1/api/books/{guid}` | Delete book | No |

Example: Create Book
Request:
POST /v1/api/books
Content-Type: application/json

```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "publisher": "Prentice Hall",
  "publicationYear": 2008,
  "genre": "Technology",
  "pageCount": 464,
  "synopsis": "A handbook of agile software craftsmanship",
  "onSale": true,
  "featured": false
}

```
Response: 201 Created
```json
{
  "guid": "a3f2b8c1-4d5e-6f7g-8h9i-0j1k2l3m4n5o",
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "publisher": "Prentice Hall",
  "publicationYear": 2008,
  "genre": "Technology",
  "pageCount": 464,
  "synopsis": "A handbook of agile software craftsmanship",
  "onSale": true,
  "featured": false,
  "details": []
}


```
Book Details Endpoints
| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/v1/api/books-details/{bookGuid}/details` | Add format to book | No |
| `GET` | `/v1/api/books-details` | List all details | No |
| `GET` | `/v1/api/books-details/{guid}` | Get detail by GUID | No |
| `PUT` | `/v1/api/books-details/{guid}` | Update detail | No |
| `DELETE` | `/v1/api/books-details/{guid}` | Delete detail | No |

POST /v1/api/books-details/{bookGuid}/details
Content-Type: application/json

```json
{
  "bookType": "PHYSICAL",
  "price": 59.90,
  "stockQuantity": 25
}

```
Response: 201 Created
```json
{
  "guid": "b4c3d2e1-5f6g-7h8i-9j0k-1l2m3n4o5p6q",
  "bookType": "PHYSICAL",
  "price": 59.90,
  "discountedPrice": 53.91,
  "stockQuantity": 25,
  "bookGuid": "a3f2b8c1-4d5e-6f7g-8h9i-0j1k2l3m4n5o"
}

```
### 🔧 Configuration Profiles
Local Profile (default)
```json
{
  "guid": "b4c3d2e1-5f6g-7h8i-9j0k-1l2m3n4o5p6q",
  "bookType": "PHYSICAL",
  "price": 59.90,
  "discountedPrice": 53.91,
  "stockQuantity": 25,
  "bookGuid": "a3f2b8c1-4d5e-6f7g-8h9i-0j1k2l3m4n5o"
}

```

## ⚙️ Profiles and Environment Setup
The application supports two profiles:

- **Local** (default)
  - Uses **H2 database** for quick development and testing
  - Active by default
- **Production**
  - Uses **MySQL database**

  Switch profiles via Spring Boot command:

```bash
# Run with local profile
mvn spring-boot:run -Dspring-boot.run.profiles=local

# Run with production profile
mvn spring-boot:run -Dspring-boot.run.profiles=prod

```


## 🚀 Quick Start

Install Java 21 and Maven

Clone the repository:

bash
git clone https://github.com/laismanieri/bookstore-api-refactored.git
cd bookstore-api-refactored
Run with the local profile (H2):

bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
Access the API at:

http://localhost:8080/v1/api/books

http://localhost:8080/v1/api/books-details

Optional:

Use Postman to import the collection and test all endpoints.

Use H2 Console (/h2-console) to inspect data when running with the local profile.

---
  ## 📑 API Documentation

 - Controllers: Handle only HTTP requests and responses
 - Service layer: Handles business rules, validation, logging, and exceptions
 - Mapper: Converts between Entities and DTOs
  - Repository: Handles persistence to H2/MySQL
  - GlobalExceptionHandler: Centralized error handling via @ControllerAdvice
  - Validation: Bean Validation and custom validators ensure input correctnes

---
## 📄 License

Licensed under the MIT License for educational and personal use.

---

## 👨‍💻 Contributor

| [<img src="https://avatars.githubusercontent.com/u/82177551?s=96&v=4" width=115><br><sub>Lais Manieri</sub>](https://github.com/laismanieri) |  
| :---: |
