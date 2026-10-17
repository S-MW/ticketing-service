# Ticketing Service

Design and build the back end of a simple **support ticketing system** with:

- **Users & roles** — register / login with JWT, three roles: `Customer`, `Agent`, `Monitor`
- **Tickets** — customers create tickets, agents view / update / resolve them
- **Ticket lifecycle** — a `NEW` ticket expires after **30 seconds**, expired tickets are removed ( soft delete ) after **3 minutes**
- **Logs & auditing** — every ticket status change and every login ( success / failure ) is logged, readable only by `Monitor` users


## Tech Stack

- Java 17
- Spring Boot (Web MVC, Data JPA, Security, Scheduling)
- PostgreSQL + Liquibase
- JWT
- MapStruct, Lombok
- Swagger ( springdoc-openapi )
- Docker ( app + PostgreSQL + pgAdmin )
- Maven


## Run the application

Pick the option that matches what you have installed. In all of them the app starts on http://localhost:8080, and Liquibase creates the tables and seeds the roles automatically on the first run.

| Option | You need |
|--------|----------|
| [A. Docker only](#option-a-docker-only) | Docker Desktop |
| [B. Java + Docker database](#option-b-java--docker-database) | Java 17+ and Docker Desktop |
| [C. Java + your own PostgreSQL](#option-c-java--your-own-postgresql) | Java 17+ and PostgreSQL |

### Option A: Docker only

Builds and runs the app, PostgreSQL and pgAdmin in containers. No Java needed.

```bash
docker compose up -d --build
```

The first build downloads the Maven dependencies, so it takes a few minutes.

### Option B: Java + Docker database

Start only PostgreSQL and pgAdmin:

```bash
docker compose up -d postgres pgadmin
```

Then run the app:

```bash
./mvnw spring-boot:run
```

On Windows ( cmd / PowerShell ) use `mvnw.cmd spring-boot:run`.

### Option C: Java + your own PostgreSQL

1. Create a database and a user, for example:

   ```sql
   CREATE USER ticketing WITH PASSWORD 'ticketing';
   CREATE DATABASE ticketing OWNER ticketing;
   ```

2. Run the app. If your database, user or password are different, pass them as environment variables ( no need to edit `application.properties` ):

   ```bash
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/ticketing SPRING_DATASOURCE_USERNAME=ticketing SPRING_DATASOURCE_PASSWORD=ticketing ./mvnw spring-boot:run
   ```

   On Windows PowerShell set them first, then run:

   ```powershell
   $env:SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/ticketing"
   $env:SPRING_DATASOURCE_USERNAME="ticketing"
   $env:SPRING_DATASOURCE_PASSWORD="ticketing"
   .\mvnw.cmd spring-boot:run
   ```

### Docker services

| Service    | URL / Host                | Login                                   |
|------------|---------------------------|-----------------------------------------|
| App        | http://localhost:8080     | —                                       |
| PostgreSQL | `localhost:5432`          | db `ticketing`, user `ticketing`, password `ticketing` |
| pgAdmin    | http://localhost:5050     | `admin@example.com` / `admin` — the `ticketing` server is already added ( password `ticketing` ) |

> If you already have containers named `ticketing-postgres` / `ticketing-pgadmin` created without compose, remove them first: `docker rm -f ticketing-postgres ticketing-pgadmin` ( the data stays in the `ticketing-pgdata` volume ).

### Try the API

**Swagger UI:** http://localhost:8080/swagger-ui.html

1. `POST /api/v1/users/register` — create a user ( `role_name`: `Customer`, `Agent` or `Monitor` )
2. `POST /api/v1/users/login` — copy the `token` from the response
3. Click **Authorize** and paste the token ( without `Bearer ` )

**Postman:** import [`postman/ticketing-service.postman_collection.json`](postman/ticketing-service.postman_collection.json)

- Run the requests from top to bottom ( or **Run collection** ).
- It registers 3 demo users ( `customer@example.com`, `agent@example.com`, `monitor@example.com`, password `Password_123` ).
- Login requests save the tokens, and Create Ticket saves the `ticketId`, into the collection variables automatically.

### Stop

```bash
docker compose down
```

The data is kept in the Docker volumes. Use `docker compose down -v` to delete it.


## API Endpoints

| Method | Endpoint                          | Role     | Description                              |
|--------|-----------------------------------|----------|------------------------------------------|
| POST   | `/api/v1/users/register`          | public   | Register a user                          |
| POST   | `/api/v1/users/login`             | public   | Login, returns a JWT                     |
| POST   | `/api/v1/tickets`                 | Customer | Create a ticket                          |
| GET    | `/api/v1/tickets`                 | Agent    | Get all tickets                          |
| GET    | `/api/v1/tickets/{id}`            | Agent    | Get a ticket by id                       |
| PATCH  | `/api/v1/tickets/{id}`            | Agent    | Update the ticket text                   |
| PATCH  | `/api/v1/tickets/{id}/resolve`    | Agent    | Resolve a `NEW` ticket                   |
| GET    | `/api/v1/logs/ticket-status`      | Monitor  | Ticket status change log ( `page`, `size` ) |
| GET    | `/api/v1/logs/logins`             | Monitor  | Login history log ( `page`, `size` )     |


## References

Tutorials and docs used for each subject while building this project.

| Subject   | Reference                                                                                     |
|-----------|-----------------------------------------------------------------------------------------------|
| Liquibase | https://contribute.liquibase.com/extensions-integrations/directory/integration-docs/springboot/ |
| Liquibase | https://docs.liquibase.com/secure/reference-guide-5-2-2/change-types/sqlfile |
| validation | https://medium.com/paysafe-bulgaria/springboot-dto-validation-good-practices-and-breakdown-fee69277b3b0 |
| validation groups | https://saurav-sanyal.medium.com/using-groups-for-conditional-validation-in-spring-boot-b561c0f377db |
| mapstruct | https://www.baeldung.com/spring-mapstruct-mapper-bean-creation |
| Spring Securit with JWT | https://www.geeksforgeeks.org/springboot/spring-boot-3-0-jwt-authentication-with-spring-security-using-mysql-database/ |
| Spring Securit with JWT | https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication/tree/master |
| postgresql enum | https://www.postgresql.org/docs/current/datatype-enum.html |
| scheduled  | https://www.baeldung.com/spring-scheduled-tasks |
