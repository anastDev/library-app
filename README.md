# 📚 Library App

A RESTful backend API for managing a library system — built with **Java** and **Spring Boot**,
following production-style architecture and best practices.

The system supports role-based access control, book inventory management, member
registration and a borrowing workflow. A React frontend is currently in development
and will connect to the deployed backend.

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- MySQL running locally
- Gradle

### Setup

```bash
# Clone the repository
git clone https://github.com/anastDev/library-app.git
cd library-app
```

```bash
# Run the application
./gradlew bootRun
```

---

## 🛠️ Tech Stack

- **Java** + **Spring Boot**
- **MySQL** — relational database with JPA/Hibernate
- **Spring Security** + **JWT** — authentication and role-based access control
- **Swagger / OpenAPI** — full API documentation
- **JPA Auditing** — automatic created/updated timestamps on entities
- **Lombok** — reduced boilerplate
- **Gradle** — build tool

---

## 🏗️ Architecture

Follows a layered Controller–Service–Repository pattern with DTOs to separate
API contracts from internal domain models.

---

## 👥 Roles

| Role | Permissions |
|---|---|
| 🔑 **Admin** | Full CRUD on books and members |
| 📖 **Member** | Browse books, borrow books |
| 👤 **Guest** | Browse books only |

Authentication is handled via JWT. Role-based access is enforced at the endpoint level.

---

## 📡 API Endpoints

### Books — `/api/books`

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | `/api/books` | Get all books | Public |
| GET | `/api/books/{isbn}` | Get book by ISBN | Public |
| POST | `/api/books` | Add a new book | 🔑 Admin |
| PUT | `/api/books` | Update a book | 🔑 Admin |
| DELETE | `/api/books/{isbn}` | Delete a book by ISBN | 🔑 Admin |

### Members — `/api/members`

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | `/api/members` | Get all members | 🔑 Admin |
| POST | `/api/members` | Register a new member | 🔑 Admin |
| DELETE | `/api/members/{uuid}` | Delete a member | 🔑 Admin |

### Borrowed — `/api/borrowed`

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | `/api/borrowed/member/{uuid}` | Get borrowed books by member | 🔑 Admin / Member |
| GET | `/api/borrowed/book/{isbn}` | Get borrow history by book | 🔑 Admin |
| POST | `/api/borrowed` | Borrow a book | 📖 Member |

---

## ⚠️ Error Handling

The API returns structured error responses across all endpoints:

- `400` — Validation error
- `404` — Entity not found
- `409` — Conflict (entity already exists)
- `500` — Internal server error

---

## 📄 API Documentation

Swagger UI is available [here](http://localhost:8080/swagger-ui/index.html)

---

## 🗺️ Roadmap

- [x] Book CRUD with ISBN-based lookup
- [x] Member registration and management
- [x] Borrow a book with copy availability check
- [x] JWT authentication and role-based access control
- [x] Swagger / OpenAPI documentation
- [x] JPA Auditing (created/updated timestamps)
- [ ] Return book endpoint (reduces available copies)
- [ ] React frontend connected to deployed backend