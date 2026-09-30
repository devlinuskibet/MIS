# Church Management System - REST API Documentation

Base URL: `http://localhost:9001/api`

---

## 🔑 Authentication Endpoints

### 1. Admin Login
- **Endpoint:** `POST /api/login`
- **Description:** Authenticates admin credentials and returns a JWT bearer token.
- **Request Body:**
  ```json
  {
    "email": "admin@church.org",
    "password": "your_password"
  }
  ```
- **Response (200 OK):**
  ```json
  {
    "message": "Login successful",
    "role": "admin",
    "success": true,
    "token": "eyJhbGciOiJIUzI1NiJ9..."
  }
  ```

---

## 👥 Member Management Endpoints

### 1. Get All Members
- **Endpoint:** `GET /api/members`
- **Response (200 OK):** Array of member registration objects.

### 2. Create New Member
- **Endpoint:** `POST /api/members`
- **Request Body:**
  ```json
  {
    "fullName": "Jonathan Doe",
    "email": "jonathan@example.com",
    "phone": "+123456789",
    "dob": "1995-04-12",
    "address": "789 Hope Street"
  }
  ```

### 3. Get Member By ID
- **Endpoint:** `GET /api/members/{id}`

### 4. Update Member
- **Endpoint:** `PUT /api/members/{id}`

### 5. Delete Member
- **Endpoint:** `DELETE /api/members/{id}`

---

## 📅 Event Management Endpoints

### 1. Get All Events
- **Endpoint:** `GET /api/events`

### 2. Create Event
- **Endpoint:** `POST /api/events`
- **Request Body:**
  ```json
  {
    "title": "Sunday Celebration Service",
    "location": "Main Auditorium",
    "description": "Weekly worship, sermon, and fellowship.",
    "time": "09:00:00"
  }
  ```

### 3. Update Event
- **Endpoint:** `PUT /api/events/{id}`

### 4. Delete Event
- **Endpoint:** `DELETE /api/events/{id}`

---

## 📢 Announcement Management Endpoints

### 1. Get Active Announcements
- **Endpoint:** `GET /api/announcements/active`

### 2. Create Announcement
- **Endpoint:** `POST /api/announcements`

### 3. Delete Announcement
- **Endpoint:** `DELETE /api/announcements/{id}`

---

## 📖 Sermon Endpoints

### 1. Get All Sermons
- **Endpoint:** `GET /api/sermons`

### 2. Add Sermon Record
- **Endpoint:** `POST /api/sermons`
- **Request Body:**
  ```json
  {
    "title": "Overcoming Obstacles with Faith",
    "speaker": "Pastor Samuel",
    "url": "https://media.church.org/sermons/overcoming.mp3",
    "description": "An inspiring sermon on resilience."
  }
  ```
