# Putting It Together

A comprehensive Spring Boot application demonstrating Domain-Driven Design (DDD) architecture with full CRUD operations for Address, Person, and Employer entities.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Web Interface](#web-interface)
- [Database](#database)
- [Development](#development)
- [Docker Support](#docker-support)

## 🎯 Overview

This Spring Boot application showcases a complete enterprise-grade system with:

- **Domain-Driven Design** architecture inspired by `recap_oct_24`
- **Three main entities**: Address, Person, and Employer with proper relationships
- **Dual interfaces**: REST API and web-based CRUD operations
- **Persistent file-based database** using H2
- **Modern web UI** with Bootstrap 5 and Thymeleaf
- **Java 23** support with MapStruct and Lombok

## ✨ Features

### Core Entities

#### 🏠 Address Entity
- **Comprehensive address fields**: street, number, zipCode, city, province, country
- **Formatted address display** for easy reading
- **Search functionality** by city or street
- **Validation** for required fields

#### 👤 Person Entity
- **Personal information**: firstName, lastName, dateOfBirth, gender
- **Relationships**: Links to Address and Employer entities
- **Age calculation** based on date of birth
- **Gender enum**: MALE, FEMALE, OTHER, PREFER_NOT_TO_SAY
- **Smart search** by first name, last name, or both

#### 🏢 Employer Entity
- **Company information**: name, industry, website, email, phone
- **Contact integration**: Clickable email and phone links
- **Industry categorization** with visual badges
- **Search functionality** by name or industry

### Technical Features

- **Complete CRUD Operations** for all entities
- **RESTful API** with proper HTTP methods
- **Web-based Management Interface** with Bootstrap styling
- **Real-time Form Previews** for better UX
- **Search and Filter Capabilities**
- **Persistent File-based Database**
- **Exception Handling** with user-friendly error messages
- **Responsive Design** that works on all devices

## 🏗️ Architecture

The application follows Domain-Driven Design principles with a layered architecture:

```
src/main/java/be/ipeters/puttingittogether/
├── address/
│   ├── model/              # Domain models
│   ├── jpa/
│   │   ├── entity/         # JPA entities
│   │   ├── repository/     # Data repositories
│   │   └── mapper/         # MapStruct mappers
│   ├── persistence/        # Persistence facades
│   └── service/           # Domain services
├── person/
│   └── [same structure as address]
├── employer/
│   └── [same structure as address]
├── web/
│   ├── api/v1/            # REST controllers
│   └── mvc/               # MVC controllers for web UI
└── config/                # Configuration classes
```

### Design Patterns Used

- **Domain-Driven Design (DDD)**
- **Repository Pattern**
- **Service Layer Pattern**
- **Facade Pattern** (Persistence facades)
- **Mapper Pattern** (MapStruct)
- **MVC Pattern** (Spring MVC)

## 🛠️ Technologies

### Backend
- **Java 23** - Latest Java features
- **Spring Boot 3.3.4** - Application framework
- **Spring Data JPA** - Data persistence
- **H2 Database** - File-based database
- **MapStruct 1.6.2** - Bean mapping
- **Lombok** - Boilerplate code reduction

### Frontend
- **Thymeleaf** - Server-side templating
- **Bootstrap 5.3.0** - Responsive CSS framework
- **JavaScript** - Interactive form previews

### Build & Tools
- **Maven** - Dependency management and build
- **Docker** - Containerization support

## 🚀 Getting Started

### Prerequisites

- **Java 23** (Zulu OpenJDK recommended)
- **Maven 3.6+**
- **Git**

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd puttingItTogether
   ```

2. **Set Java 23 environment** (macOS)
   ```bash
   export JAVA_HOME=/Library/Java/JavaVirtualMachines/zulu-23.jdk/Contents/Home
   export PATH=$JAVA_HOME/bin:$PATH
   ```

3. **Build the application**
   ```bash
   mvn clean package -DskipTests
   ```

4. **Run the application**
   ```bash
   # Option 1: Use the helper script
   ./run-with-java23.sh
   
   # Option 2: Run directly
   java -jar target/puttingItTogether-0.0.1-SNAPSHOT.jar
   ```

5. **Access the application**
   - **Web Interface**: http://localhost:8686/home
   - **H2 Console**: http://localhost:8686/h2-console

### Quick Start with Helper Script

The project includes a convenient script that sets up the correct Java environment:

```bash
chmod +x run-with-java23.sh
./run-with-java23.sh
```

## 📚 API Documentation

### Base URL
```
http://localhost:8686/api/v1
```

### Address API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/addresses` | List all addresses |
| GET | `/addresses/{id}` | Get address by ID |
| POST | `/addresses` | Create new address |
| PUT | `/addresses/{id}` | Update address |
| DELETE | `/addresses/{id}` | Delete address |
| GET | `/addresses/search?term=...` | Search addresses |

**Example Address JSON:**
```json
{
  "id": 1,
  "street": "Main Street",
  "number": "123",
  "zipCode": "1000",
  "city": "Brussels",
  "province": "Brussels-Capital Region",
  "country": "Belgium"
}
```

### Person API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/persons` | List all persons |
| GET | `/persons/{id}` | Get person by ID |
| POST | `/persons` | Create new person |
| PUT | `/persons/{id}` | Update person |
| DELETE | `/persons/{id}` | Delete person |
| GET | `/persons/search/firstName/{name}` | Search by first name |
| GET | `/persons/search/lastName/{name}` | Search by last name |
| GET | `/persons/search/gender/{gender}` | Search by gender |
| GET | `/persons/search/address/{addressId}` | Search by address |
| GET | `/persons/search/employer/{employerId}` | Search by employer |

**Example Person JSON:**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "dateOfBirth": "1985-06-15",
  "gender": "MALE",
  "addressId": 1,
  "employerId": 1
}
```

### Employer API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/employers` | List all employers |
| GET | `/employers/{id}` | Get employer by ID |
| POST | `/employers` | Create new employer |
| PUT | `/employers/{id}` | Update employer |
| DELETE | `/employers/{id}` | Delete employer |
| GET | `/employers/search/name/{name}` | Search by exact name |
| GET | `/employers/search/industry/{industry}` | Search by industry |
| GET | `/employers/search?term=...` | Search by name (contains) |

**Example Employer JSON:**
```json
{
  "id": 1,
  "name": "Tech Corp",
  "industry": "Technology",
  "website": "https://techcorp.com",
  "email": "contact@techcorp.com",
  "phone": "+32 2 123 45 67"
}
```

## 🌐 Web Interface

### Main Pages

- **Home** (`/home`) - Dashboard with entity counts and navigation
- **Address Management** (`/addresses`) - Full CRUD interface for addresses
- **Person Management** (`/persons`) - Full CRUD interface for persons
- **Employer Management** (`/employers`) - Full CRUD interface for employers

### Features

- **Responsive Bootstrap Design** - Works on all devices
- **Real-time Form Previews** - See changes as you type
- **Smart Search** - Filter by different criteria
- **Relationship Management** - Link persons to addresses and employers
- **Success/Error Messages** - Clear feedback for all operations
- **Confirmation Dialogs** - Prevent accidental deletions

## 🗄️ Database

### Configuration

The application uses H2 file-based database for persistence:

- **Database URL**: `jdbc:h2:file:~/databaseH2/togetherdb`
- **Username**: `sa`
- **Password**: `password`
- **Database File**: `~/databaseH2/togetherdb.mv.db`

### H2 Console

Access the database console at http://localhost:8686/h2-console

**Connection Settings:**
- **JDBC URL**: `jdbc:h2:file:~/databaseH2/togetherdb`
- **User Name**: `sa`
- **Password**: `password`

### Schema

The application automatically creates the following tables:
- `ADDRESS` - Address information
- `PERSON` - Person information with foreign keys
- `EMPLOYER` - Employer information

## 🔧 Development

### Project Structure

```
puttingItTogether/
├── src/
│   ├── main/
│   │   ├── java/be/ipeters/puttingittogether/
│   │   │   ├── address/           # Address domain
│   │   │   ├── person/            # Person domain
│   │   │   ├── employer/          # Employer domain
│   │   │   ├── web/               # Controllers
│   │   │   └── config/            # Configuration
│   │   └── resources/
│   │       ├── templates/         # Thymeleaf templates
│   │       ├── static/            # CSS, JS, images
│   │       └── application.properties
│   └── test/                      # Test classes
├── target/                        # Build output
├── Dockerfile                     # Docker configuration
├── run-with-java23.sh            # Helper script
├── pom.xml                       # Maven configuration
└── README.md                     # This file
```

### Building

```bash
# Compile only
mvn clean compile

# Package (creates JAR)
mvn clean package -DskipTests

# Run tests
mvn test

# Clean build directory
mvn clean
```

### Configuration

Key configuration files:
- `application.properties` - Application settings
- `pom.xml` - Dependencies and build configuration
- `WebConfiguration.java` - CORS and MVC settings

## 🐳 Docker Support

### Build Docker Image

```bash
docker build -t putting-it-together .
```

### Run with Docker

```bash
docker run -p 8686:8686 putting-it-together
```

The Dockerfile uses OpenJDK 23 and includes the complete build process.

## 📊 Application Properties

```properties
# Server Configuration
server.port=8686
spring.application.name=puttingItTogether

# Database Configuration
spring.datasource.url=jdbc:h2:file:~/databaseH2/togetherdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.format-sql=true

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Thymeleaf
spring.thymeleaf.cache=false
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Inspired by the `recap_oct_24` project architecture
- Built with Spring Boot and modern Java practices
- UI enhanced with Bootstrap 5 framework

---

**Author**: Carl Peters  
**Version**: 1.0.0  
**Java Version**: 23  
**Spring Boot Version**: 3.3.4  
**Last Updated**: October 2024