# 📚 Book Tracking App

A full-stack, monolithic web application built with Spring Boot 3, Spring Security, and Thymeleaf. This application allows users to track their personal book collections, manage wishlists, and mark favorites in a secure, multi-user environment.

## ✨ Features

- **Secure Authentication:** Full login/logout functionality using Spring Security 6.
- **Global Book Catalog:** View a system-wide list of all book editions added by users.
- **Personal Bookshelf:** A private "My Books" section for each user to manage their collection.
- **Book Management:**
  - Add new books (Title, Author, Publisher, Publication Date) to the catalog and your personal collection.
  - Remove books from your collection.
- **Tracking Status:** Mark books on your bookshelf as "Favorite" or "Wishlist".
- **Robust Authorization:** Users can **only** view and modify their own personal bookshelf, not the bookshelves of other users.
- **Internationalization (i18n):** Full UI support for English and European Portuguese (pt-PT).

## 🛠️ Technical Stack

This project is built with a modern, idiomatic Spring Boot 3 stack.

| Category            | Technology                                                               |
| :------------------ | :----------------------------------------------------------------------- |
| **Backend**         | Spring Boot 3 (Java 17+), Spring Security 6, Spring Data JPA, Spring Web |
| **Frontend**        | Thymeleaf, HTML5, CSS3                                                   |
| **Database**        | PostgreSQL                                                               |
| **DB Migrations**   | Flyway                                                                   |
| **Dev Environment** | Docker Compose (PostgreSQL + PgAdmin)                                    |
| **Build Tool**      | Maven                                                                    |
| **Utilities**       | MapStruct (for DTO mapping)                                              |
|                     | Jakarta Validation (for form validation)                                 |

## 🚀 Getting Started

### Prerequisites

- Java 17 (or newer)
- Apache Maven
- Docker and Docker Compose
- Git

### 1. Clone the Repository

```bash
git clone https://github.com/ivoencarnacao/book-tracking-app.git
cd book-tracking-app
```

### 2\. Start the Development Environment

The local development environment (database and admin tool) is managed by Docker Compose.

```bash
docker compose up -d
```

This command will start:

- **PostgreSQL 17** on `localhost:5432`.
- **PgAdmin 4** on `http://localhost:5050`.

### 3\. Run the Application

The Spring Boot application is configured to connect to the Dockerized PostgreSQL database out of the box.

You can run the application directly from your IDE by running the `main` method, or by using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080`.

### 4\. Seed Data

On startup, **Flyway** automatically migrates the schema and seeds the database.

Sample users (`user1`, `user2`, `guest`) and a collection of books are pre-loaded to demonstrate the application's features.

---

## 🏗️ Project Highlights & Architectural Decisions

This project serves as a demonstration of a modern, secure, and idiomatic Spring Boot application.

- **Secure by Design:** Implements robust authentication and authorization. It uses **method-level security (`@PreAuthorize`)** to prevent Insecure Direct Object Reference (IDOR) vulnerabilities, ensuring users can only modify their own data.
- **Professional Database Management:** Uses **Flyway** for deterministic schema version control and data seeding. Hibernate is set to `spring.jpa.hibernate.ddl-auto=validate` to guarantee consistency between JPA entities and the Flyway-managed schema.
- **Performant by Default:** Proactively solves the N+1 query problem by using `JOIN FETCH` in custom **Spring Data JPA** (`@Query`) queries to efficiently load entities and their associations.
- **Modern Java & Spring:** Built on Java 17, using modern features like **Java Records** for immutable DTOs.
- **Clean Code & Mappable:** Employs **MapStruct** for clean, boilerplate-free mapping between JPA Entities and DTOs and **Jakarta Validation** for server-side form validation.
