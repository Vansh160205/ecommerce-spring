# 🛒 E-Commerce Spring Boot Application

This is a full-featured e-commerce backend built using **Spring Boot** and **PostgreSQL**, designed to support core functionalities such as user management, product catalog, cart, orders, and secure authentication via JWT.

---

## 🔧 Tech Stack

* **Backend**: Spring Boot
* **Database**: PostgreSQL
* **Security**: Spring Security with JWT
* **Build Tool**: Maven
* **ORM**: JPA / Hibernate

---

## ✅ Features

* 🢁 User registration and login (with JWT authentication)
* 👨‍💼 Role-based access (`USER`, `ADMIN`)
* 📦 Product and category management
* 🛍️ Shopping cart functionality
* 📃 Order placement and history
* 🔒 Secure endpoints (JWT-protected)

---

## 📂 Project Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com.yourdomain.ecommerce/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── config/
│   └── resources/
│       ├── application.properties
│       └── static/
```

---

## 🔑 Authentication

This project uses **JWT-based authentication**. After a successful login, a token is returned and should be included in the `Authorization` header of future requests:

```
Authorization: Bearer <your_token_here>
```

---

## 🧪 Running the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/Vansh160205/ecommerce-spring.git
   cd ecommerce-spring
   ```

2. Create a PostgreSQL database and update `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   ```

3. Build and run:

   ```bash
   ./mvnw spring-boot:run
   ```

---

## 📊 API Endpoints

Add a reference to Postman collection or Swagger UI link if available.

---

## 📖 License

This project is licensed under the MIT License.
