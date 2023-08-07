The Customer Information System is a Java-based application that allows you to manage customer records, including their personal details.
It provides features such as adding, updating, deleting, and retrieving customer information, all through a RESTful API.

Features:
- Add a new customer with details like first name, last name, email, username, and password.
- Update customer information by providing their unique ID.
- Delete a customer using their ID.
- Retrieve a list of all customers or a specific customer by ID.
- Monitor the progress of customer data processing using Actuator endpoints.

Technologies Used:
- Java
- Spring Boot
- Spring Data JPA
- RESTful API
- MySQL
- Mockito for testing

Usage:
- Access the application endpoints through the API at `http://localhost:8085`

Endpoints:
- `GET /customers`: Get a list of all customers
- `GET /customers/{id}`: Get customer details by ID
- `GET /customers/names/{name}`: Get a list of customers by first name
- `POST /save`: Save a new customer
- `POST /saveAll`: Save a list of customers
- `PUT /update/{id}`: Update customer details by ID
- `DELETE /{id}`: Delete customer by ID
- `GET /actuator/progress`: View progress tracking information

