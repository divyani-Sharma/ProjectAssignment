# Java Mocking System

This project facilitates mocking of Java libraries such that their functionality is overridden without making any change in the business logic code. It supports two modes: RECORD and REPLAY.

## Project Structure

This repository contains two main projects:
1. `springboot-app`: A Spring Boot application that handles HTTP requests and interacts with a MySQL database.
2. `java-agent`: A Java agent implemented using Byte Buddy to intercept HTTP and database calls for RECORD and REPLAY modes.

## Features

- **Record Mode**: Real outbound HTTP calls are made, and real data is inserted into the database. The requests and responses are logged.
- **Replay Mode**: The application runs without external services (databases and API calls), using hardcoded values instead.

## How It Works

- **Spring Boot Application**: 
  - Exposes an endpoint `POST /api/createNewPost`.
  - Inserts a post into the MySQL database.
  - Makes an outbound HTTP call to get the current time in Asia/Kolkata timezone.
  - Returns the database record and the HTTP response.

- **Java Agent**:
  - Intercepts database and HTTP calls.
  - Logs the interactions in RECORD mode.
  - Returns hardcoded values in REPLAY mode.

## Getting Started

### Prerequisites

- Docker
- Docker Compose
- Java 11
- Maven

### Building the Projects

1. **Clone the repository**:
    ```sh
    git clone https://github.com/yourusername/java-mocking-system.git
    cd java-mocking-system
    ```

2. **Build the Spring Boot application**:
    ```sh
    cd springboot-app
    mvn clean install
    cd ..
    ```

3. **Build the Java agent**:
    ```sh
    cd java-agent
    mvn clean install
    cd ..
    ```

### Running the Application

1. **Docker Compose**:
    - Ensure Docker and Docker Compose are installed and running.
    - Run the following command to start the services:
        ```sh
        docker-compose up --build
        ```

2. **Environment Variables**:
    - The application uses environment variables for configuration.
    - Set the following environment variables:
        - `HT_MODE`: Mode of operation (`RECORD` or `REPLAY`).
        - `SPRING_DATASOURCE_URL`: MySQL database URL.
        - `SPRING_DATASOURCE_USERNAME`: MySQL username.
        - `SPRING_DATASOURCE_PASSWORD`: MySQL password.

### API Endpoints

- **Create New Post**:
    - **Endpoint**: `POST /api/createNewPost`
    - **Request Body**:
        ```json
        {
            "post_name": "<some-unique-name>",
            "post_contents": "<some-random-string>"
        }
        ```
    - **Response**:
        ```json
        {
            "db_post": {
                "id": 1,
                "name": "<some-unique-name>",
                "contents": "<some-random-string>"
            },
            "http_outbound": {
                "timezone": "Asia/Kolkata",
                "datetime": "2024-07-04T12:34:56.789"
            }
        }
        ```

## References

- [Byte Buddy Documentation](https://bytebuddy.net/#/)
- [OpenTelemetry Project](https://github.com/open-telemetry/opentelemetry-java)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

## Acknowledgements

- Inspired by the concepts of monkey patching and bytecode manipulation.

