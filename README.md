# Project Name Readme

Welcome to **Project Name**! This repository contains an application built with a modern tech stack for [describe what the application does briefly].

## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Tech Stack](#tech-stack)
- [Endpoints](#endpoints)
- [Authentication](#authentication)

## Getting Started

Project Overview
Welcome to the categoryEntity and ProductEntity List application! This enterprise-grade application is designed to provide users with an intuitive way to interact with a vast collection of productEntities and categories.

<br>The application's core features empower users to:

<ul>Browse through a paginated list of productEntities, complete with logos.</ul>

<ul>Discover unique ProductEntity names, enabling quick exploration.</ul>
<ul>Retrieve all sites based on a selected categoryEntity name.</ul>
<ul>Search for productEntities by their names, facilitating efficient navigation.</ul>
<ul>Edit ProductEntity details, including names and logos (limited to users with the "EDITOR" role).</ul>


Follow these instructions to get the project up and running on your local machine.

### Prerequisites

Make sure you have the following tools installed:

- Docker ([Install Docker](https://docs.docker.com/get-docker/))

### Installation

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/BodyakovDeveloper/home-koval-party.git
Navigate to the project directory:

bash
Copy code
   ```bash
  cd project-name
   ```
Run the application and database using Docker:


bash
Copy code
  ```bash
docker-compose up
```
This will set up the required services and dependencies.

Usage
After successfully starting the application, you can access the available endpoints through the Swagger UI:

Open your web browser and go to http://localhost:8080/swagger-ui/index.html#/
## Tech Stack

The project utilizes the following technologies and libraries:

- **Spring Boot**: Framework for building robust Java applications.
- **Spring Data JPA**: Provides easy integration with databases using the Java Persistence API.
- **Spring Security**: Ensures security features like authentication and authorization.
- **Spring Validation**: Adds validation support for request data.
- **JWT**: JSON Web Token for secure communication.
- **PostgreSQL**: Relational database management system.
- **Liquibase**: Database migration and version control tool.
- **Lombok**: Reduces boilerplate code.
- **MapStruct**: Simplifies Java bean mappings.
- **JUnit**: Testing framework.
- **Testcontainers**: Provides lightweight, disposable containers for integration testing.
- **SpringDoc**: Generates API documentation using Spring Web MVC and Swagger UI.

Endpoints
The application exposes various endpoints for different functionalities. Please refer to the Swagger UI for a complete list of available endpoints and their descriptions.

Authentication
The application uses JWT-based authentication. You can use the following predefined users for testing:

role "EDITOR":

```
{
  "username": "editor",
  "password": "123"
}
```

role "USER":

```
{
  "username": "user",
  "password": "123"
}
```

You can use these credentials to interact with the authentication endpoint.