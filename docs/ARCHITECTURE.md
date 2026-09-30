# System Architecture & Database Design

## 🏗️ Architecture Overview

The **Church Management System (CHMIS)** backend follows a standard 4-tier layered architecture:

```mermaid
graph TD
    Client[Frontend Client / Postman] -->|HTTP REST / JSON| Controllers[REST Controller Layer]
    Controllers -->|DTO Mapping| Services[Service Business Logic Layer]
    Services -->|Domain Models| Repositories[Spring Data JPA Repository Layer]
    Repositories -->|JDBC SQL Queries| Database[(PostgreSQL Database)]
```

### Layer Descriptions

1. **Controller Layer (`com.example.church_management_system.controller`)**
   - Handles incoming HTTP REST requests.
   - Enforces endpoint mappings and CORS configuration.
   - Delegates business execution to services.

2. **Service Layer (`com.example.church_management_system.service`)**
   - Contains core domain logic and transactional boundaries (`@Transactional`).
   - Handles DTO <-> Entity mappings.
   - Executes validation and security logic.

3. **Repository Layer (`com.example.church_management_system.repository`)**
   - Provides JPA data access methods extending `JpaRepository`.
   - Executes standard CRUD and custom derived query methods.

4. **Domain Model Layer (`com.example.church_management_system.model`)**
   - JPA Entities mapped to PostgreSQL tables (`member_registration`, `event`, `announcement`, `sermon`, etc.).

---

## 🗄️ Database Entity Relationship (ER) Diagram

```mermaid
erDiagram
    ADMIN {
        bigint id PK
        string email
        string password
    }

    MEMBER_REGISTRATION {
        bigint id PK
        string full_name
        string email
        string phone
        date dob
        string address
        date date_joined
    }

    MEMBER_CONTRIBUTION {
        bigint id PK
        bigint member_id FK
        string member_name
        string contribution_type
        numeric amount
        string mode_of_payment
        timestamp date_of_contribution
    }

    TITHES_AND_OFFERINGS {
        bigint id PK
        numeric amount
        timestamp date_of_contribution
    }

    EVENT {
        bigint id PK
        string title
        string location
        text description
        date date
        time time
    }

    ANNOUNCEMENT {
        bigint id PK
        string announcement_type
        string title
        text message
        timestamp date_created
        date effective_date
        date effective_until
        string target_audience
    }

    SERMON {
        bigint id PK
        string title
        string speaker
        date date
        string url
        text description
    }

    MEMBER_REGISTRATION ||--o{ MEMBER_CONTRIBUTION : "makes"
```
