

# spring-kotlin-jwt-redis-template

A clean and extensible Spring Boot project template written in **Kotlin**, built with **Hexagonal Architecture**, **DDD principles**, and secure authentication using **JWT** and **Redis**.

## 🧱 Architecture

This project follows the **Hexagonal Architecture (Ports and Adapters)**, which enables strong separation of concerns and easier maintainability.

```plaintext
├── adapter │ 
   ├── in-web # REST controllers 
   └── out-persistent # JPA/Hibernate persistence adapters 
├── application 
   ├── port # Application layer interfaces
      ├── in # UseCase interfaces  
      └── out # Persistence abstraction (e.g., save/load) 
   └── service # Application service implementations
└── domain # Core domain model (entities, logic) 
```

### 🚧 Notice
Only sign-up and duplicate email check features have been implemented at this stage.
Please consider refactoring and extending for more detailed functionalities in the future.


### 🚀 Getting Started
Run with Docker Compose
You can run the required infrastructure (Redis and MySQL) using Docker Compose:

```dockerfile
version: '3.8'

services:
  redis:
    image: 'redis:latest'
    ports:
      - '6379:6379'

  mysql:
    image: 'mysql:latest'
    environment:
      - 'MYSQL_DATABASE=templateV1'
      - 'MYSQL_USER=yunhwan'
      - 'MYSQL_PASSWORD=secret'
      - 'MYSQL_ROOT_PASSWORD=verysecret'
    ports:
      - '3306:3306'
```

just run the following command in the directory where the `compose.yaml` file is located:
