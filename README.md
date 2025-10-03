# Elevate-Labs-Task-7
# Java JDBC – Employee Database App

## 📌 Objective
A simple **Java JDBC application** that connects to a **PostgreSQL database** and performs **CRUD operations** on an Employee table.  
This project demonstrates database connectivity, SQL queries, and JDBC usage.

## 🛠 Tools & Technologies
- Java  
- PostgreSQL  
- JDBC Driver (PostgreSQL Connector JAR)  
- IDE: VS Code / Eclipse / IntelliJ  

## 🚀 Features
- **Add Employee** – Insert new employee records  
- **View Employees** – Display all employees from the database  
- **Update Employee** – Modify salary of an employee by ID  
- **Delete Employee** – Remove an employee record by ID  
- **Menu-driven Console App**  

## 📂 Project Structure
All code is written in a single file:

EmployeeDBApp.java

sql
Copy code

## 📋 Database Setup (PostgreSQL)
Run the following commands in PostgreSQL:

```sql
CREATE DATABASE testdb;

\c testdb   -- connect to database

CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    salary DOUBLE PRECISION
);
▶️ How to Run
Install PostgreSQL and create the database & table (see setup above).

Download the PostgreSQL JDBC driver from:
PostgreSQL JDBC Driver

Add the JDBC driver JAR to your project’s classpath.

Update database connection details in the code:

java
Copy code
private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
private static final String USER = "postgres";
private static final String PASSWORD = "your_password";
Compile and run:

bash
Copy code
javac EmployeeDBApp.java
java EmployeeDBApp
📸 Outcome
Choose from menu options to add, view, update, or delete employees.

Data is stored in PostgreSQL database.
