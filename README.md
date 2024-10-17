
# Jabberwocky Gym Microservices

## Overview

The **Jabberwocky Gym** system is composed of five primary microservices:
1. **Member Service**: Manages gym members' information and registrations.
2. **Booking Service**: Handles the booking of workout classes by members.
3. **Workout Class Service**: Manages the workout class schedules and details.
4. **Notification Service**: Sends notifications based on booking-related events.
5. **Real-Time Analytics Service**: Tracks real-time booking events for analytics.

These services operate independently and communicate using REST APIs and Kafka for event-driven communication.

## Technologies Used
- **Java 21**: Built with the latest features of Java.
- **Spring Boot**: Framework for developing the RESTful APIs.
- **Spring Data JPA**: Simplifies interaction with relational databases.
- **Apache Kafka**: Used for event-driven communication.
- **Kafka Streams**: Used to process real-time booking events.
- **H2 Database**: In-memory database for development and testing.
- **PostgreSQL**: Used in the Notification Service.
- **Docker**: (Optional) For containerization.

---

## Services and Communication

### Member Service
Manages members' registration, updates, and retrieval of member details.
- Port: `8081`

### Booking Service
Handles the creation, updating, and cancellation of bookings.
- Port: `8082`

### Workout Class Service
Manages workout class schedules and details.
- Port: `8083`

### Notification Service
Listens to booking events and sends notifications.
- Port: `8084`

### Real-Time Analytics Service
Tracks booking events and provides real-time analytics.
- Port: `8085`

---

## How Kafka Communication Works

### Booking Microservice
When a booking event occurs (create, update, or cancel), the **Booking Service** publishes these events as Kafka messages to the respective topics:
- `booking-created`
- `booking-updated`
- `booking-cancelled`

### Notification Service
The **Notification Service** consumes Kafka events from the `booking-created`, `booking-updated`, and `booking-cancelled` topics. Upon receiving these events, it processes them to send relevant notifications to members and stores them for future reference.

### Real-Time Analytics Service
The **Real-Time Analytics Service** listens to the same Kafka topics (`booking-created` and `booking-cancelled`) and updates counters for real-time monitoring of booking statistics.

---

## Running the Services

### Clone the Repositories
```bash
git clone https://github.com/Luke-Arthur/318-Part-C
```

### Navigate to Each Service Directory
After cloning, navigate to each service directory:

```bash
cd workoutclass-service
cd member-service
cd booking-service
cd notification-service
cd real-time-analytics-service
```

### Build and Run Each Service
To build and run each service, use the following command:
```bash
./mvnw spring-boot:run
```

### Service Ports:
- **Member Service**: Default port `8081`
- **Booking Service**: Default port `8082`
- **Workout Class Service**: Default port `8083`
- **Notification Service**: Default port `8084`
- **Real-Time Analytics Service**: Default port `8085`

---

## API Endpoints

### Member Service API
- `GET /members`: Retrieve all members.
- `GET /members/{id}`: Retrieve a specific member by ID.
- `POST /members`: Register a new member.
- `DELETE /members/{id}`: Delete a member by ID.

### Booking Service API
- `GET /bookings`: Retrieve all bookings.
- `GET /bookings/{id}`: Retrieve a specific booking by ID.
- `POST /bookings`: Create a new booking.
- `DELETE /bookings/{id}`: Cancel a booking by ID.

### Workout Class Service API
- `GET /classes`: Retrieve all workout classes.
- `GET /classes/{id}`: Retrieve a specific workout class by ID.
- `POST /classes`: Create a new workout class.
- `DELETE /classes/{id}`: Delete a workout class by ID.

### Notification Service API
- `GET /api/notifications`: Retrieve all notifications.

### Real-Time Analytics Service API
- `GET /bookings/created`: Get the total count of bookings created.
- `GET /bookings/cancelled`: Get the total count of bookings cancelled.

---

## Example Use Cases

- **Scenario 1**: A member books a class. The **Booking Service** publishes a `BookingCreatedEvent`. The **Notification Service** sends a booking confirmation, and the **Real-Time Analytics Service** updates the total count of bookings created.
- **Scenario 2**: A member cancels a booking. The **Booking Service** publishes a `BookingCancelledEvent`. The **Notification Service** sends a cancellation notification, and the **Real-Time Analytics Service** updates the total count of bookings cancelled.
---

## Configuration

Each service has an `application.properties` file located in `src/main/resources/` where you can configure service-specific properties like ports, database settings, and Kafka server details.

---

## Contributors
- Tony Li - 7576663
- David Stevens - 7627920
- Justin Landrigan - 7471567
- Luke Moorhouse - 7603599
