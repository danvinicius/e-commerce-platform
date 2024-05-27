# E-commerce platform API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

This project is an API built using **Java, Java Spring, Flyway Migrations, PostgresSQL as the database, and Spring Security and JWT for authentication control.**

The aim of this project is to practice Java and Spring Framework with authentication, microservices and testing concepts.

<h4> 
    🚧 Under development 🚧
</h4>

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Database](#database)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/danvinicius/e-commerce-platform-api.git
```

2. Install dependencies with Maven

3. Install [PostgresSQL](https://www.postgresql.org/)

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080


## API Endpoints
The API provides the following endpoints (API's base url is /api):

### Product
```markdown
GET /product - Retrieve a list of all products. (all users)

GET /product/{id} - Retrieve a product by its id. (all users)

POST /product - Register a new product. (ADMIN access required)

PUT /product/{id} - Update a product by its id. (ADMIN access required)

DELETE /product/{id} - Delete a product by its id. (ADMIN access required)
```
### Category
```markdown
GET /category - Retrieve a list of all categories. (all users)

GET /category/{id} - Retrieve a category by its id. (all users)

POST /category - Register a new category. (ADMIN access required)

PUT /category/{id} - Update a category by its id. (ADMIN access required)

DELETE /category/{id} - Delete a category by its id. (ADMIN access required)
```
### Shipping address
```markdown
GET /address - Retrieve a list of all addresses. (ADMIN access required)

GET /address/{id} - Retrieve an address by its id. (all authenticated users - address must belong to user)

POST /address - Register a new address. (all authenticated users)

PUT /address/{id} - Update an address by its id. (all authenticated users - address must belong to user)

DELETE /address/{id} - Delete an address by its id. (all authenticated users - address must belong to user)
```
### Order
```markdown
GET /order - Retrieve a list of all orders. (ADMIN access required)

GET /order/{id} - Retrieve an order by its id. (all authenticated users - order must belong to user)

POST /order - Register a new order. (all authenticated users)

PUT /order/{id} - Update an order by its id. (all authenticated users - order must belong to user)

PUT /order/{id}/cancel - Cancel an order by its id. (all authenticated users - order must belong to user)

DELETE /order/{id} - Delete an order by its id. (all authenticated users - order must belong to user)
```
### Cart
```markdown
GET /cart - Retrieve a list of all carts. (ADMIN access required)

GET /cart/{id} - Retrieve an cart by its id. (all authenticated users - cart must belong to user)

POST /cart - Register a new cart. (all authenticated users)

PUT /cart/{id}/item/add - Add a new item to cart. (all authenticated users - cart must belong to user)

PUT /cart/{id}/item/remove - Remove an item from cart. (all authenticated users - cart must belong to user)

DELETE /cart/{id} - Delete an cart by its id. (all authenticated users - cart must belong to user)
```
### User
```markdown
GET /user - Retrieve a list of all users. (ADMIN access required)

GET /user/{id} - Retrieve an user by its id. (ADMIN access required)

GET /user/me - Retrieve all info about request user. (all authenticated users)

POST /user - Register a new user. (all users)

PUT /user/{id} - Update a user by its id. (all authenticated users - user can only update himself)

DELETE /user/{id} - Delete a user by its id. (all authenticated users - user can only delete himself)

POST /auth - Login to the app. (all users)
```

## Authentication
The API uses Spring Security for authentication control. The following roles are available:

```
USER -> Standard user role for logged-in users.
ADMIN -> Admin role.
```
To access protected endpoints as an ADMIN user, provide the appropriate authentication credentials in the request header.

## Database
The project utilizes [PostgresSQL](https://www.postgresql.org/) as the database. The necessary database migrations are managed using Flyway.
