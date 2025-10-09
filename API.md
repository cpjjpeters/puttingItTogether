# API Documentation

Complete REST API documentation for the Putting It Together application.

## Base URL

```
http://localhost:8686/api/v1
```

## Authentication

Currently, no authentication is required. All endpoints are publicly accessible.

## Response Format

All responses follow a consistent JSON format:

### Success Response
```json
{
  "data": { /* response data */ },
  "status": 200
}
```

### Error Response
```json
{
  "timestamp": "2024-10-09T14:31:12.000+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/api/v1/addresses/999"
}
```

---

## Address API

### List All Addresses
**GET** `/addresses`

Returns all addresses in the system.

**Response Example:**
```json
[
  {
    "id": 1,
    "street": "Main Street",
    "number": "123",
    "zipCode": "1000",
    "city": "Brussels",
    "province": "Brussels-Capital Region",
    "country": "Belgium"
  }
]
```

### Get Address by ID
**GET** `/addresses/{id}`

**Parameters:**
- `id` (path) - Address ID

**Response Example:**
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

### Create Address
**POST** `/addresses`

**Request Body:**
```json
{
  "street": "Main Street",
  "number": "123",
  "zipCode": "1000",
  "city": "Brussels",
  "province": "Brussels-Capital Region",
  "country": "Belgium"
}
```

**Required Fields:**
- `street`
- `number`
- `zipCode`
- `city`
- `country`

**Optional Fields:**
- `province`

### Update Address
**PUT** `/addresses/{id}`

**Parameters:**
- `id` (path) - Address ID

**Request Body:** Same as Create Address

### Delete Address
**DELETE** `/addresses/{id}`

**Parameters:**
- `id` (path) - Address ID

### Search Addresses
**GET** `/addresses/search?term={searchTerm}`

**Parameters:**
- `term` (query) - Search term (searches in city and street fields)

---

## Person API

### List All Persons
**GET** `/persons`

**Response Example:**
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "dateOfBirth": "1985-06-15",
    "gender": "MALE",
    "addressId": 1,
    "employerId": 1,
    "address": null
  }
]
```

### Get Person by ID
**GET** `/persons/{id}`

**Parameters:**
- `id` (path) - Person ID

### Create Person
**POST** `/persons`

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "dateOfBirth": "1985-06-15",
  "gender": "MALE",
  "addressId": 1,
  "employerId": 1
}
```

**Required Fields:**
- `firstName`
- `lastName`

**Optional Fields:**
- `dateOfBirth` (format: YYYY-MM-DD)
- `gender` (enum: MALE, FEMALE, OTHER, PREFER_NOT_TO_SAY)
- `addressId` (foreign key to Address)
- `employerId` (foreign key to Employer)

### Update Person
**PUT** `/persons/{id}`

**Parameters:**
- `id` (path) - Person ID

**Request Body:** Same as Create Person

### Delete Person
**DELETE** `/persons/{id}`

**Parameters:**
- `id` (path) - Person ID

### Search Persons by First Name
**GET** `/persons/search/firstName/{firstName}`

**Parameters:**
- `firstName` (path) - First name to search for

### Search Persons by Last Name
**GET** `/persons/search/lastName/{lastName}`

**Parameters:**
- `lastName` (path) - Last name to search for

### Search Persons by Gender
**GET** `/persons/search/gender/{gender}`

**Parameters:**
- `gender` (path) - Gender to search for (MALE, FEMALE, OTHER, PREFER_NOT_TO_SAY)

### Search Persons by Address
**GET** `/persons/search/address/{addressId}`

**Parameters:**
- `addressId` (path) - Address ID

### Search Persons by Employer
**GET** `/persons/search/employer/{employerId}`

**Parameters:**
- `employerId` (path) - Employer ID

---

## Employer API

### List All Employers
**GET** `/employers`

**Response Example:**
```json
[
  {
    "id": 1,
    "name": "Tech Corp",
    "industry": "Technology",
    "website": "https://techcorp.com",
    "email": "contact@techcorp.com",
    "phone": "+32 2 123 45 67"
  }
]
```

### Get Employer by ID
**GET** `/employers/{id}`

**Parameters:**
- `id` (path) - Employer ID

### Create Employer
**POST** `/employers`

**Request Body:**
```json
{
  "name": "Tech Corp",
  "industry": "Technology",
  "website": "https://techcorp.com",
  "email": "contact@techcorp.com",
  "phone": "+32 2 123 45 67"
}
```

**Required Fields:**
- `name`

**Optional Fields:**
- `industry`
- `website` (must be valid URL format)
- `email` (must be valid email format)
- `phone`

### Update Employer
**PUT** `/employers/{id}`

**Parameters:**
- `id` (path) - Employer ID

**Request Body:** Same as Create Employer

### Delete Employer
**DELETE** `/employers/{id}`

**Parameters:**
- `id` (path) - Employer ID

### Search Employers by Exact Name
**GET** `/employers/search/name/{name}`

**Parameters:**
- `name` (path) - Exact company name

### Search Employers by Industry
**GET** `/employers/search/industry/{industry}`

**Parameters:**
- `industry` (path) - Industry name

### Search Employers by Name (Contains)
**GET** `/employers/search?term={searchTerm}`

**Parameters:**
- `term` (query) - Search term (case-insensitive partial match)

---

## Error Codes

| Status Code | Description |
|-------------|-------------|
| 200 | OK - Request successful |
| 400 | Bad Request - Invalid request data |
| 404 | Not Found - Resource not found |
| 500 | Internal Server Error - Server error |

## Data Types

### Gender Enum
- `MALE`
- `FEMALE`
- `OTHER`
- `PREFER_NOT_TO_SAY`

### Date Format
All dates use ISO 8601 format: `YYYY-MM-DD`

Example: `2024-10-09`

## CORS Configuration

The API supports CORS with the following configuration:
- **Allowed Origins**: `*` (all origins)
- **Allowed Methods**: `GET`, `POST`, `PUT`, `DELETE`, `PATCH`, `OPTIONS`
- **Allowed Headers**: `*` (all headers)
- **Allow Credentials**: `true`
- **Max Age**: `3600` seconds

## Rate Limiting

Currently, no rate limiting is implemented.

## Validation Rules

### Address
- `street`: Required, max 255 characters
- `number`: Required, max 50 characters
- `zipCode`: Required, max 20 characters
- `city`: Required, max 100 characters
- `province`: Optional, max 100 characters
- `country`: Required, max 100 characters

### Person
- `firstName`: Required, max 100 characters
- `lastName`: Required, max 100 characters
- `dateOfBirth`: Optional, valid date format
- `gender`: Optional, must be valid enum value
- `addressId`: Optional, must reference existing address
- `employerId`: Optional, must reference existing employer

### Employer
- `name`: Required, max 255 characters
- `industry`: Optional, max 100 characters
- `website`: Optional, must be valid URL format
- `email`: Optional, must be valid email format
- `phone`: Optional, max 50 characters

## Examples with curl

### Create an Address
```bash
curl -X POST http://localhost:8686/api/v1/addresses \
  -H "Content-Type: application/json" \
  -d '{
    "street": "Main Street",
    "number": "123",
    "zipCode": "1000",
    "city": "Brussels",
    "province": "Brussels-Capital Region",
    "country": "Belgium"
  }'
```

### Create a Person
```bash
curl -X POST http://localhost:8686/api/v1/persons \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "dateOfBirth": "1985-06-15",
    "gender": "MALE",
    "addressId": 1,
    "employerId": 1
  }'
```

### Create an Employer
```bash
curl -X POST http://localhost:8686/api/v1/employers \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Tech Corp",
    "industry": "Technology",
    "website": "https://techcorp.com",
    "email": "contact@techcorp.com",
    "phone": "+32 2 123 45 67"
  }'
```

### Search Addresses
```bash
curl "http://localhost:8686/api/v1/addresses/search?term=Brussels"
```

### Get All Persons
```bash
curl http://localhost:8686/api/v1/persons
```