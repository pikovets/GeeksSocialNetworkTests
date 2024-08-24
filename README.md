## 1. Introduction
The Selenium project for **GeeksSocialNetwork** is designed to automate the testing of the platform’s user interface and core functionalities, ensuring a seamless and bug-free experience for users. This project focuses on running end-to-end tests for critical user interactions, such as authentication, post creation, commenting, and more.

Currently, the test suite includes comprehensive tests for the authentication service, verifying that user registration, login, and password recovery features are working as expected. These tests play a crucial role in maintaining the integrity of the user access system, which is fundamental to the security and usability of the social network.

By leveraging Selenium, we can simulate real user behavior in a web browser, interact with the application just like a user would, and validate that the application responds correctly. This automated testing approach helps us catch issues early in the development process, leading to faster iteration cycles and a more reliable product.

![image](https://github.com/user-attachments/assets/203f4e0d-ca7d-4805-bfb8-5fac0b21267f)


## 2. Cloning the Repositories
To run this project you need to clone all parts of this project into one folder:
```
git clone https://github.com/pikovets/GeeksSocialNetworkAPI.git
git clone https://github.com/pikovets/GeeksSocialNetworkUI.git
git clone https://github.com/pikovets/GeeksSocialNetworkTests.git
```

## 3. Project Structure Overview

**Backend:** Describe the backend project, its technology stack, and the main functionality<br>

**Frontend:** Describe the frontend project, its technology stack, and main features<br>

**Selenium Tests:** Explain the Selenium tests project, what it covers, and how it interacts with the frontend and backend<br>

**docker-compose.yml:** File to start all services with one command<br>

## 4. Explaining  Docker Compose File
The docker-compose.yml file sets up the necessary services for the project. Here’s a breakdown of each service:

```
version: '3.9'
services:
  frontend:
    container_name: 'frontend'
    build: ./frontend
    depends_on:
      - backend
    ports:
      - "8080:8080"
    networks:
      - my_network
      
  backend:
    container_name: 'backend'
    build: ./backend
    environment:
      DATABASE_HOST: postgres
    depends_on:
      - postgres
    ports:
      - "3000:3000"
    networks:
      - my_network
      
  postgres:
    container_name: 'postgres'
    image: postgres
    restart: always
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: social_network_db
    volumes:
      - ./postgres/docker_postgres_init.sql:/docker-entrypoint-initdb.d/docker_postgres_init.sql
      - ./postgres/data:/var/lib/postgresql/data
    ports:
      - "5432:5432"
    networks:
      - my_network
      
  rabbitmq:
    hostname: "rabbitmq"
    container_name: 'rabbit-mq'
    image: rabbitmq:3-management-alpine
    restart: always
    ports:
      - "5672:5672"
      - "15672:15672"
    volumes:
      - rabbitmq_data:/var/lib/rabbitmq
    networks:
      - my_network
      
  chrome:
    image: selenium/node-chrome:dev
    container_name: 'chrome-browser'
    shm_size: 2gb
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
    networks:
      - my_network

  selenium-hub:
    image: selenium/hub:latest
    container_name: 'selenium-hub'
    ports:
      - "4442:4442"
      - "4443:4443"
      - "4444:4444"
    networks:
      - my_network

volumes:
  rabbitmq_data:

networks:
  my_network:
    driver: bridge
```

Service Descriptions:

**Frontend:** Serves the user interface and depends on the backend service.

**Backend:** Manages data operations and provides API access, dependent on PostgreSQL.

**Postgres:** The database service used for data persistence.

**RabbitMQ:** Message broker for handling asynchronous communications.

**Chrome and Selenium Hub:** Provide the environment for running Selenium tests.

## 5. Running the Projects
Command to build and run the Docker Compose setup:
```docker-compose up --build```

## 6. Stopping and Cleaning Up
Command to stop the containers:<br>
```docker-compose down```

Command to remove all containers, networks, and volumes to start fresh: <br>
```docker-compose down --volumes --remove-orphans```
