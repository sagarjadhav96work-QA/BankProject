# Bank Management System

## Description

This project is a web-based Bank Management System tested using both manual and automation testing techniques. The framework is designed to ensure the reliability, functionality, security, and scalability of banking operations through robust automated test execution.

---

## Modules

### 1. User

* Account registration and login
* View account details
* Check account balance
* View transaction history

### 2. Admin

* Manage users and accounts
* Approve or reject account requests
* Monitor system activities
* Generate reports

### 3. Cashier

* Handle deposits and withdrawals
* Process transactions
* Update account balances
* Assist users with banking operations

---

## Features

* Account creation and management
* Transaction processing
* Report generation
* Role-based access control (User, Admin, Cashier)

---

## Automation Framework Features

### Framework Design

* Implemented **Page Object Model (POM)** design pattern for better code maintainability and reusability.
* Built a scalable and modular automation framework using Java, Selenium WebDriver, and TestNG.
* Externalized test data and configuration using properties files.

### Parallel Execution

* Implemented **ThreadLocal WebDriver** to support thread-safe parallel execution.
* Enabled execution of multiple test cases simultaneously using TestNG parallel execution.

### Flaky Test Handling

* Implemented **Retry Analyzer** to automatically rerun failed test cases caused by intermittent application or environment issues.
* Reduced false test failures and improved execution reliability.

### Selenium Grid & Docker Integration

* Configured **Selenium Grid 4** for distributed test execution.
* Created **Docker Compose YAML** configuration to spin up:

  * Selenium Hub
  * Chrome Node
  * Firefox Node
  * Edge Node
* Enabled cross-browser compatibility testing through Dockerized Selenium Grid.

### CI/CD Integration

* Integrated framework with **Jenkins** for Continuous Integration and Continuous Testing.
* Configured automated test execution through Jenkins pipelines.
* Generated execution reports after every build.

### Reporting & Logging

* Integrated Extent Reports for detailed execution reporting.
* Implemented Log4j logging for debugging and execution tracking.
* Captured screenshots automatically for failed test cases.

### Database Validation

* Performed backend validation using MySQL queries.
* Validated UI data against database records.

### API Testing

* Validated API responses and status codes.
* Performed end-to-end verification between UI, API, and Database layers.

---

## Technologies Used

### Automation Tools

* Java
* Selenium WebDriver
* TestNG
* Maven

### CI/CD & Containerization

* Jenkins
* Docker
* Docker Compose
* Selenium Grid 4

### Database

* MySQL

### Reporting & Logging

* Extent Reports
* Log4j

### Version Control

* Git
* GitHub

---

## Testing Scope

### Manual Testing

* Functional Testing
* Regression Testing
* UI Testing
* Exploratory Testing

### Automation Testing

* Cross-Browser Testing
* Parallel Execution Testing
* Regression Automation Suite

### API Testing

* API Validation
* Response Verification

### Database Testing

* Data Integrity Validation
* SQL Query Verification

### Security Testing

* Input Validation
* XSS Testing

---

## How to Run

### Local Execution

1. Clone the repository
2. Import the project into Eclipse/IntelliJ
3. Update configuration in `config.properties`
4. Execute `testng.xml`

### Selenium Grid Execution

1. Start Docker Desktop
2. Run Docker Compose:

   ```bash
   docker compose up -d
   ```
3. Verify Grid:

   ```
   http://localhost:4444
   ```
4. Set execution mode to Remote
5. Execute TestNG suite

### Jenkins Execution

1. Configure Jenkins job
2. Connect GitHub repository
3. Configure Maven build steps
4. Execute automated test suite

---

## Author

**Sagar Jadhav**

QA Automation Engineer
