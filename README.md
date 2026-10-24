# CropX Full-Stack Application

A runnable starter implementation of CropX using Spring Boot, Java 17, Spring Data JPA, an H2 file database, and a responsive HTML/CSS/JavaScript frontend served by Spring Boot.

## Features
- Crop CRUD: create, list, edit, delete
- Buyer orders: create, list, edit, delete
- Delivery tracking: create, list, edit, delete
- Dashboard summary API
- Persistent local H2 database (`./data/cropxdb`)
- Responsive frontend at `http://localhost:8080`

## Requirements
- Java 17 or newer
- Maven 3.8+ installed
- Internet on first run so Maven can download dependencies

## Run it
1. Extract `CropX_FullStack.zip`.
2. Open a terminal / Command Prompt inside the extracted `CropX_FullStack` folder.
3. Run:
   - Windows: `mvnw.cmd spring-boot:run` if you add a Maven wrapper, or `mvn spring-boot:run`
   - macOS/Linux: `mvn spring-boot:run`
4. Wait for the log line indicating Spring Boot has started.
5. Open `http://localhost:8080`.

If `mvn` is not recognized, install Maven or import the folder as an Existing Maven Project in Eclipse and run `CropXApplication.java` as a Java Application.

## API endpoints
- `GET /api/dashboard`
- `GET /api/crops`, `POST /api/crops`, `GET /api/crops/{id}`, `PUT /api/crops/{id}`, `DELETE /api/crops/{id}`
- `GET /api/orders`, `POST /api/orders`, `PUT /api/orders/{id}`, `DELETE /api/orders/{id}`
- `GET /api/deliveries`, `POST /api/deliveries`, `PUT /api/deliveries/{id}`, `DELETE /api/deliveries/{id}`

Example crop JSON:
```json
{
  "name": "Tomato",
  "quantity": 100,
  "price": 28.50,
  "location": "Hyderabad",
  "status": "Available"
}
```

Example order JSON:
```json
{
  "buyer": "Sample Buyer",
  "phone": "9999999999",
  "cropId": 1,
  "quantity": 10,
  "status": "Pending"
}
```

Example delivery JSON:
```json
{
  "orderId": 1,
  "expectedDate": "2026-10-30",
  "status": "Pending",
  "driver": "Delivery contact"
}
```

## Important notes
- This is a functional starter app, not a production-secure deployment. It has no login, role-based access control, payment processing, or cloud-hosted database.
- The H2 database is stored locally in the `data` folder. Keep that folder if you want to retain local records.
- To deploy publicly, use a hosting service that supports Java/Spring Boot and configure a persistent database. A GitHub repository alone does not run the backend.
