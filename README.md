# NeonDomain_Test

This Java project is a test for a NeonDomain job opportunity.

## Setup Instructions

To run the project successfully, ensure you have a MySQL database configured with the following parameters:

- **Port:** 3306
- **Database Name:** neondomain_db
- **Username:** admin
- **Password:** admin

## Project Execution

1. Start the project.
2. Utilize the `POST` endpoint `localhost:8080/auth/login` to initiate a login process. Provide a username and password in the request body.
   - Initial credentials: Username - `admin`, Password - `password`. These credentials are pre-created via a seeder.
   - This request will return an authentication token, which you'll use as a Bearer token in subsequent user functionality requests.

## Endpoints for Functionality

- Access the Postman collection for the users API [here](https://www.postman.com/axelbon/workspace/axel-s-public-workspace).

### Retrieving Users

- **GET** `localhost:8080/user`: Fetches the list of users.
- **GET** `localhost:8080/user/ID`: Retrieves details of a specific user by their ID.

### Managing Users

- **POST** `localhost:8080/user`: Adds a new user.
  - **Request Body:** 
    ```json
    {
        "user": "string",
        "age": "int",
        "userName": "string",
        "password": "string"
    }
    ```
- **PUT** `localhost:8080/user/ID`: Updates a user by their ID.
  - **Request Body:** 
    ```json
    {
        "user": "string",
        "age": "int",
        "userName": "string",
        "password": "string"
    }
    ```
- **DELETE** `localhost:8080/user/ID`: Deletes a user based on their ID.

## Important Notes

- Every user-related endpoint requires a Bearer token for authentication. Without it, an error will be returned.
- Use the obtained token from the login endpoint in the header as a Bearer token for accessing user functionality endpoints.
