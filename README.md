# Zooclinic Management System Backend

This project is the backend part of the zooclinic management system, developed using Java, Spring Boot, MySQL, and Hibernate.

## Features

- **Animals**:
    - Create, read, update, and delete animals.
- **Appointments**:
    - Create, read, update, and delete appointments.
    - Associate appointments with animals.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Maven

## Requirements

- Java 11+
- MySQL
- Maven

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/zooclinic-management-system.git
    cd zooclinic-management-system/backend
    ```

2. Configure the MySQL database in `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/zooclinic
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Build and run the backend:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Directory Structure

```plaintext
backend/
├── src/
│   ├── main/
│   │   ├── java/com/zooclinic/zooclinic/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   ├── resources/
│   │   │   ├── application.properties
├── pom.xml
```

## API Endpoints

### Animals

- POST /animal
    - Creates a new animal.


- GET /animal
    - Lists all animals.


- GET /animal/{id}
    - Retrieves details of a specific animal.


- PUT /animal/{id}
    - Updates an existing animal.


- DELETE /animal/{id}
  - Deletes an animal.

### Appointments

- GET /animal/appointment
    - Lists all appointments.


- GET /animal/{animalId}/appointment
    - Retrieves details of a specific appointment.


- POST /animal/{animalId}/appointment
    - Creates a new appointment.


- PUT /animal/{animalId}/appointment
    - Updates an existing appointment.


- DELETE /animal/{animalId}/appointment
    - Deletes an appointment.