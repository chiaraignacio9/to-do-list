# ToDo List (Backend)

This is a little project basad in Rest Api.

Dependency's used:
- Spring Boot
- Spring WEB
- Spring Security
- Spring Data JPA
- JWT

The database used is PostgreSQL.

This project contain 5 end point:
* POST /register = create a new user account 
* POST /login = login with user credentials
* POST /task = create a new task using jwt token for identify the user
* GET /task = get all task where user extraed by token is parented
* DELETE /task{id} = delete task by id

The Endpoints ' /task/** ' are protected, only authenticated user 
