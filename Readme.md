# PROJECT NAME 
Social Media Blog API (Spring Boot Version)

## Project Description

Build RESTful APIs to manage a Social Media Blog with user accounts and messages using Spring Boot.

## Technologies Used

* Java - version 11
* Spring Boot - version 2.5.5
* Spring Web - version 5.3.10
* Spring Data JPA
* H2 Database
* JUnit - version 5.7.2

## Features

* User Account Management
  - User registration functionality
  - User authentication/login system

* Message Operations
  - Create new messages
  - Update existing messages by ID
  - Delete messages by ID
  - Retrieve all messages in the system

* Message Retrieval
  - Get a specific message by its ID
  - Get all messages from a specific user account

* Error Handling
  - Appropriate HTTP status codes for various scenarios
  - Custom exceptions (AccountNotFoundException, AlreadyExistsException)
  - Proper exception handling across controller layer

* RESTful API Design
  - RESTful endpoint structure following resource-based URL patterns
  - Proper HTTP methods (POST, GET, PATCH, DELETE) for CRUD operations
  - JSON request/response formatting for data exchange

To-do list:
* Add comprehensive unit and integration tests
* Add swagger for API documentation
* Implement pagination for message retrieval endpoints

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven
- Git

### Clone the Repository
```bash
git clone https://github.com/yourusername/social-media-blog-spring.git
cd social-media-blog-spring
```

### Setup Database
This project uses H2 in-memory database which is automatically configured when you run the application.

### Build the Project

**Windows:**
```cmd
mvn clean package
```

**Unix/Linux/macOS:**
```bash
./mvnw clean package
```

### Run the Application

**Windows:**
```cmd
mvn spring-boot:run
```

**Unix/Linux/macOS:**
```bash
./mvnw spring-boot:run
```

Alternatively, you can run the JAR file:

```bash
java -jar target/Challenges-1.1.jar
```

### API Testing
Once the application is running, you can test the API endpoints using tools like Postman or cURL.

The API will be available at:
```
http://localhost:8080
```

Example request to get all messages:

**Windows (PowerShell):**
```powershell
Invoke-RestMethod -Uri http://localhost:8080/messages -Method Get
```

**Unix/Linux/macOS:**
```bash
curl -X GET http://localhost:8080/messages
```

### Expected Output

When the application starts successfully, you should see output similar to:
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.5)

[main] INFO com.example.Application - Starting Application using Java 11
[main] INFO com.example.Application - No active profile set, falling back to default profiles: default
[main] INFO o.s.b.w.embedded.tomcat.TomcatWebServer - Tomcat initialized with port(s): 8080 (http)
...
[main] INFO o.s.b.w.embedded.tomcat.TomcatWebServer - Tomcat started on port(s): 8080 (http) with context path ''
[main] INFO com.example.Application - Started Application in 2.789 seconds (JVM running for 3.173)
```

If you're using the H2 console (which is typically enabled in Spring Boot development), you can access it at:
```
http://localhost:8080/h2-console
```

JDBC URL: `jdbc:h2:mem:testdb`  
Username: `sa`  
Password: (leave empty)

## Usage

Consume APIs from a client app or test your APIs with Postman.
Here is the list of valid URLs and payloads:

## API Documentation

### Summary of Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/register` | Register a new user account |
| POST | `/login` | Authenticate an existing user |
| POST | `/messages` | Create a new message |
| PATCH | `/messages/{messageId}` | Update an existing message |
| DELETE | `/messages/{messageId}` | Remove a message |
| GET | `/messages` | Retrieve all messages |
| GET | `/messages/{messageId}` | Retrieve a specific message |
| GET | `/accounts/{accountId}/messages` | Retrieve all messages from a specific user |

### Detailed Documentation

<details>
  <summary><code>POST</code> <code>/register</code> - Register a new account</summary>

#### Request Body
```json
{
  "username": "string",
  "password": "string"
}
```

#### Responses
- `200 OK` - Account created successfully
- `409 Conflict` - Username already taken
- `400 Bad Request` - Invalid input

#### Response Example (200 OK)
```json
{
  "account_id": 1,
  "username": "johndoe",
  "password": "password123"
}
```
</details>

<details>
  <summary><code>POST</code> <code>/login</code> - Login to existing account</summary>

#### Request Body
```json
{
  "username": "string",
  "password": "string"
}
```

#### Responses
- `200 OK` - Login successful
- `401 Unauthorized` - Invalid credentials

#### Response Example (200 OK)
```json
{
  "account_id": 1,
  "username": "johndoe",
  "password": "password123"
}
```
</details>

<details>
  <summary><code>POST</code> <code>/messages</code> - Create a new message</summary>

#### Request Body
```json
{
  "posted_by": 1,
  "message_text": "Hello world!",
  "time_posted_epoch": 1609459200000
}
```

#### Responses
- `200 OK` - Message created successfully
- `400 Bad Request` - Invalid input or constraints violated

#### Response Example (200 OK)
```json
{
  "message_id": 1,
  "posted_by": 1,
  "message_text": "Hello world!",
  "time_posted_epoch": 1609459200000
}
```
</details>

<details>
  <summary><code>PATCH</code> <code>/messages/{messageId}</code> - Update a message</summary>

#### Path Parameters
- `messageId` - ID of the message to update

#### Request Body
```json
{
  "message_text": "Updated message text"
}
```

#### Responses
- `200 OK` - Message updated successfully (returns number of rows affected)
- `400 Bad Request` - Message not found or invalid input

#### Response Example (200 OK)
```
1
```
</details>

<details>
  <summary><code>DELETE</code> <code>/messages/{messageId}</code> - Delete a message</summary>

#### Path Parameters
- `messageId` - ID of the message to delete

#### Responses
- `200 OK` - Message deleted successfully (returns number of rows affected)
- `400 Bad Request` - Message not found

#### Response Example (200 OK)
```
1
```
</details>

<details>
  <summary><code>GET</code> <code>/messages</code> - Get all messages</summary>

#### Responses
- `200 OK` - Returns all messages

#### Response Example (200 OK)
```json
[
  {
    "message_id": 1,
    "posted_by": 1,
    "message_text": "Hello world!",
    "time_posted_epoch": 1609459200000
  },
  {
    "message_id": 2,
    "posted_by": 2,
    "message_text": "Another message",
    "time_posted_epoch": 1609459300000
  }
]
```
</details>

<details>
  <summary><code>GET</code> <code>/messages/{messageId}</code> - Get a specific message</summary>

#### Path Parameters
- `messageId` - ID of the message to retrieve

#### Responses
- `200 OK` - Returns the requested message
- `400 Bad Request` - Message not found

#### Response Example (200 OK)
```json
{
  "message_id": 1,
  "posted_by": 1,
  "message_text": "Hello world!",
  "time_posted_epoch": 1609459200000
}
```
</details>

<details>
  <summary><code>GET</code> <code>/accounts/{accountId}/messages</code> - Get all messages by a user</summary>

#### Path Parameters
- `accountId` - ID of the user account

#### Responses
- `200 OK` - Returns all messages by the specified user

#### Response Example (200 OK)
```json
[
  {
    "message_id": 1,
    "posted_by": 1,
    "message_text": "Hello world!",
    "time_posted_epoch": 1609459200000
  },
  {
    "message_id": 3,
    "posted_by": 1,
    "message_text": "My second message",
    "time_posted_epoch": 1609459400000
  }
]
```
</details>

## License

This project uses the following license: [MIT License](https://opensource.org/licenses/MIT).
