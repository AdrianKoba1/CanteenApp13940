# 🍽️ QuickBite — University Canteen Management System

A full-stack application for managing a university canteen with a Spring Boot backend and a modern React + TanStack Router frontend.

---

## 📦 Backend Setup (Spring Boot)

### ✅ Prerequisites
- Java Development Kit **JDK 21**
- **Maven** (for dependency management)
- **PostgreSQL** database installed and running

### ⚙️ Configuration
Update `application.properties` (located in `src/main/resources`) with your local PostgreSQL database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Alternatively, **create a PostgreSQL user and database** that matches the defaults in the properties file.

### ▶️ Running the Backend

1. Make sure all Maven dependencies are installed:
   ```bash
   mvn clean install
   ```

2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
   Or run it from your IDE (like IntelliJ) using the main class:
   ```
   UniversityCanteenManagementApplication.java
   ```

---

## 💻 Frontend Setup (React + Vite + TanStack Router)

### ✅ Prerequisites
- Node.js and npm installed

### 📦 Install dependencies

1. Navigate to the frontend directory (if separate):
   ```bash
   cd frontend
   ```

2. Install required npm packages:
   ```bash
   npm install
   ```

3. If not already installed, add TanStack Router:
   ```bash
   npm install @tanstack/react-router
   ```

### ▶️ Start the Frontend
```bash
npm start
```

The app will run at:  
📍 `http://localhost:3000` (frontend)  
📍 `http://localhost:1234` (backend API)

---

## 🔑 Default Login Behavior
- The app opens on the login page (`/login`)
- Upon "login", you're redirected to the main menu

> Note: Login is currently mocked — authentication logic can be implemented as needed.

---

## 🛠 Tech Stack

- **Backend:** Spring Boot, JPA, PostgreSQL
- **Frontend:** React, MUI, TanStack Router, Redux Toolkit
- **Build Tools:** Maven, Vite

---