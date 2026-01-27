<p align="center">
  <img src="https://img.shields.io/badge/Status-In%20Progress-blueviolet"/>
  <a href="https://github.com/laismanieri">
    <img alt="Made by Lais Manieri" src="https://img.shields.io/badge/Made%20by-LaisManieri-yellow">
  </a>
</p>

# <h1 align="center">Bookstore API Evolution<br><sub>Backend Refactoring Journey (2023 → 2026)</sub></h1>

## 💻 About the Project

<p align="justify">
This project showcases my evolution as a Backend Developer, refactoring my original 2023 academic project (SENAI Bookstore) from a basic CRUD directly on entities to a professional REST API following modern Java/Spring Boot best practices.

<strong>Key improvements implemented:</strong>
- <strong>DTOs + Bean Validation</strong> (@Valid, @NotNull, @Positive, @NotBlank)
- <strong>JPA Relationships</strong> (@OneToMany/@ManyToOne with proper cascade)
- <strong>JSON Bidirectional Handling</strong> (@JsonManagedReference/@JsonBackReference)
- <strong>Spring Profiles</strong> (H2 for dev, MySQL for production-like)
- <strong>Business Logic Centralization</strong> (discountedPrice calculation in service layer)

Originally developed during my **SENAI Technical Course in Systems Development (2023)**, now refactored to demonstrate enterprise-level backend practices.
</p>

---

## ⚙️ Features

:heavy_check_mark: **Book CRUD** (Create, Read, Update, Delete)  
:heavy_check_mark: **BookDetails relationship** (multiple formats per book: hardcover, ebook, etc.)  
:heavy_check_mark: **Input validation** with Bean Validation annotations  
:heavy_check_mark: **Business rules** (automatic 10% discount calculation)  
:heavy_check_mark: **Production-ready** Spring Profiles (H2 dev / MySQL prod)  
:heavy_check_mark: **JSON serialization** handling (no infinite recursion)  

---

## 🏗 API Endpoints

```yaml
POST  /v1/api/books           # Create book (+ optional details)
GET   /v1/api/books           # List all books
GET   /v1/api/books/{id}      # Get book by ID
PUT   /v1/api/books/{id}      # Update book
DELETE /v1/api/books/{id}     # Delete book

BACKEND
├── Java 21 + Spring Boot 3.3
├── Spring Data JPA / Hibernate
├── Spring Validation
├── H2 Database (dev)
├── MySQL (production-like)
├── Lombok
└── Maven

TOOLS
├── Postman (API testing)
├── H2 Console (data visualization)
└── Git / GitHub
```
## 🚀 Quick Start

### Prerequisites

- Java 21+
- Maven 3.8+
- IDE: IntelliJ IDEA, Eclipse, or VS Code
- Postman (recommended)

### Running the Project

#### 1. Clone the repository

```bash
git clone https://github.com/laismanieri/bookstore-api-evolution.git
cd bookstore-api-evolution
```

## 👨‍💻 Contributor

| [<img src="https://avatars.githubusercontent.com/u/82177551?s=96&v=4" width=115><br><sub>Lais Manieri</sub>](https://github.com/laismanieri) |  
| :---: |
