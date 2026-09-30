# ⛪ Church Management System (CHMIS) - Backend

[![Java CI with Maven](https://github.com/devlinuskibet/MIS/actions/workflows/ci.yml/badge.svg)](https://github.com/devlinuskibet/MIS/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)](https://www.postgresql.org/)

The **Church Management System (CHMIS)** is a modern, scalable Spring Boot RESTful API backend designed to streamline administrative operations, member management, event scheduling, sermon archiving, announcements, and financial contributions.

---

## 🛠️ Technology Stack

- **Backend Framework:** Spring Boot 3.4.5
- **Language:** Java 21
- **Security & Authentication:** Spring Security with JWT (JSON Web Tokens)
- **Database & ORM:** PostgreSQL 15 & Spring Data JPA (Hibernate)
- **Database Migration/DDL:** Custom SQL Schema & Seed Data (`schema.sql` & `data.sql`)
- **Build & Dependency Management:** Apache Maven
- **Containerization:** Docker & Docker Compose
- **Testing:** JUnit 5, Mockito, MockMvc, H2 In-Memory DB (for unit/integration tests)

---

## 📁 Project Architecture

```
.
├── .github/                 # GitHub Workflows (CI pipeline & PR templates)
├── docs/                    # API Documentation & System Architecture Docs
│   ├── API_DOCUMENTATION.md # Detailed REST API Specification
│   └── ARCHITECTURE.md      # System Architecture & ER Diagrams
├── src/
│   ├── main/
│   │   ├── java/com/example/church_management_system/
│   │   │   ├── common/      # Global API Response Wrappers
│   │   │   ├── config/      # Security & JWT Configuration
│   │   │   ├── controller/  # REST Controllers
│   │   │   ├── dto/         # Data Transfer Objects (DTOs)
│   │   │   ├── exception/   # Global Exception Handling
│   │   │   ├── model/       # JPA Entities / Domain Models
│   │   │   ├── repository/  # JPA Repositories
│   │   │   └── service/     # Business Logic Layer & Implementations
│   │   └── resources/
│   │       ├── db/          # DDL Schema and Seed Data scripts
│   │       └── application*.properties # Dev, Test, Prod Profiles
│   └── test/                # Service & Controller Test Suites
├── docker-compose.yml       # Docker Compose setup for PostgreSQL
├── mvnw & mvnw.cmd          # Maven Wrapper
├── pom.xml                  # Maven POM dependencies
└── README.md                # Main Documentation
```

---

## ⚡ Core Features

- 🔐 **Admin Authentication:** Secure Login using JWT token generation & BCrypt password hashing.
- 👥 **Member Management:** Register, list, update, and manage member profiles.
- 📢 **Announcement System:** Publish church announcements with effective date ranges and targeted audiences.
- 📅 **Event Scheduling:** Plan and manage upcoming church services, choir practices, and fellowship events.
- 📖 **Sermon Archiving:** Record, search, and manage sermon audio/video links and descriptions.
- 💰 **Financial Tracking:** Manage tithes, offerings, and individual member contribution records.

---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK) 17 or 21**
- **Docker & Docker Compose** (or a local **PostgreSQL** server)
- **Git**

### 1. Clone the Repository

```bash
git clone https://github.com/devlinuskibet/MIS.git
cd MIS
```

### 2. Start PostgreSQL Container (Docker)

```bash
docker compose up -d
```

### 3. Run the Backend Application

Using Maven Wrapper:

```bash
# Development profile (connects to local PostgreSQL)
./mvnw spring-boot:run
```

Or on Windows CMD / PowerShell:

```cmd
.\mvnw.cmd spring-boot:run
```

The application will start on `http://localhost:9001`.

---

## 🧪 Running Unit & Integration Tests

To run the complete automated test suite using the isolated H2 in-memory profile:

```bash
./mvnw test -Dspring.profiles.active=test
```

---

## 📬 API Documentation & Endpoints

Comprehensive API documentation is available in [`docs/API_DOCUMENTATION.md`](docs/API_DOCUMENTATION.md).

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/login` | Authenticate admin & get JWT token |
| `GET` | `/api/members` | Get all registered members |
| `POST` | `/api/members` | Register a new member |
| `GET` | `/api/events` | List all scheduled church events |
| `POST` | `/api/events` | Create a new event |
| `GET` | `/api/announcements/active` | Get active announcements |
| `GET` | `/api/sermons` | Retrieve sermon archive |

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
