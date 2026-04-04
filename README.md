# 💼 Loan Management System

## 📌 Description

A **full-stack Loan Management System** developed using **Java Spring Boot** for backend and **HTML, CSS, JavaScript** for frontend.
This application enables users to apply for loans and allows admins to manage approvals, track loan status, repayments, and generate reports efficiently.

---

## 🚀 Features

* 🔐 User Registration & Login
* 📝 Loan Application Submission (with file upload)
* 👨‍💼 Admin Dashboard (Approve / Reject Loans)
* 💰 EMI Calculation (after approval)
* 📊 Loan Status Tracking
* 📈 Reports & Analytics (Loan summary, EMI, overdue loans)
* 💳 Loan Repayment Module
* 🌐 Multi-language Support (English / Tamil)
* 📂 File Upload (Address proof, Income proof)
* 👥 Role-Based Access (Admin & Customer)

---

## 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* REST APIs

### Frontend

* HTML
* CSS
* JavaScript

### Database

* PostgreSQL (Render Cloud)

### Tools & Deployment

* Maven
* Git & GitHub
* Render (Cloud Deployment)

---

## 🌍 Live Demo

🔗 https://loan-management-system-1-5z32.onrender.com

---

💡 Note:
The application is deployed on Render. Since it uses the free tier, the server may go to sleep after inactivity and take a few seconds to start again (cold start).

---

## ▶️ How to Run (Local)

### 1️⃣ Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/loan-management-system.git
cd loan-management-system
```

### 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/loan
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3️⃣ Run the application

```bash
mvn spring-boot:run
```

### 4️⃣ Open in browser

```
http://localhost:8080
```

---

## 🔑 Default Admin Credentials

```
Username: admin1
Password: admin123
```

---

## 📂 Project Structure

```
src/main/java              → Backend (Controllers, Services, Models)
src/main/resources/static → Frontend (HTML, CSS, JS)
src/main/resources        → Configuration files
pom.xml                   → Maven dependencies
```

---

## 📸 Key Modules

* User Authentication
* Loan Application Module
* Admin Approval System
* Loan Reports & Analytics
* Repayment Management

---

## 🎯 Future Enhancements

* JWT Authentication 🔐
* Email Notifications 📧
* Payment Gateway Integration 💳
* Advanced Dashboard Charts 📊

---

## 👩‍💻 Author

**Poorvaja S**
MCA Student | Full Stack Developer

---

## ⭐ Conclusion

This project demonstrates a complete **end-to-end full-stack application**, including frontend, backend, database, and cloud deployment — making it a strong portfolio project for software development roles.
