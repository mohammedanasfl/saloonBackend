# Saloon API

This is a RESTful API for managing a saloon, including employees, customers, products, and sales records.

## Features
- Employee Management (Add, Retrieve Employees)
- Customer Management (Add, Retrieve Customers)
- Product Management (Add, Retrieve Products)
- Sales Record Management (Record and Retrieve Sales Transactions)

## Technologies Used
- **Java** (Spring Boot)
- **Maven (Dependency Management)
- **JPA/Hibernate** (Database ORM)
- **MySQL (Database)
- **Swagger** (API Documentation)

## Installation
### Prerequisites:
- Java 17+
- Maven
- MySQL (Configured in `application.properties`)

## API Endpoints

### Employee Endpoints
- `POST /api/employees` - Add a new employee
- `GET /api/employees` - Retrieve all employees

### Customer Endpoints
- `POST /api/customers` - Add a new customer
- `GET /api/customers` - Retrieve all customers

### Product Endpoints
- `POST /api/products` - Add a new product
- `GET /api/products` - Retrieve all products

### Sales Record Endpoints
- `POST /api/sales` - Create a new sales record
- `GET /api/sales` - Retrieve all sales records

## Sample JSON Request
```json
{
  "employeeId": 1,
  "customerPhone": 1234567890,
  "saleItems": [
    {
      "productId": 101,
      "productName": "Shampoo",
      "productPrice": 200.0,
      "quantity": 2
    }
  ]
}
```

## Running Tests
Run tests using:
```bash
mvn test
```



