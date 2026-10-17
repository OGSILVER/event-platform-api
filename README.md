# Event Platform API

Simple Spring Boot API for an event platform. The code follows the structure of the existing messaging project:

`controllers` -> `services` -> `repositories` -> `models`, with request/response objects in `dto`.

## Database

The application expects PostgreSQL with:

- database: `event_platform`
- username: `event_platform`
- password: the value configured in `src/main/resources/application.properties`
- port: `5432`

On the Linux machine where PostgreSQL is installed:

```sql
CREATE USER event_platform WITH PASSWORD 'choose-a-strong-password';
CREATE DATABASE event_platform OWNER event_platform;
```

Then replace `spring.datasource.password` with the same password. If PostgreSQL is on another host, replace `localhost` in `spring.datasource.url` with that host's address.

`spring.jpa.hibernate.ddl-auto=update` creates/updates the four simple tables from the entities while developing. For production, use migrations instead of relying on this setting.

## Run

```bash
./mvnw spring-boot:run
```

The API runs on port `9091`. Swagger UI is available at `http://localhost:9091/swagger-ui.html`.

## Endpoints

- `POST /api/participants`, `GET /api/participants`, `GET /api/participants/{id}`
- `POST /api/locations`, `GET /api/locations`, `GET /api/locations/{id}`
- `POST /api/events`, `GET /api/events`, `GET /api/events/{id}`
- `PUT /api/events/{id}`, `DELETE /api/events/{id}`
- `POST /api/events/{eventId}/registrations?participantId={participantId}`
- `GET /api/events/{eventId}/registrations`
