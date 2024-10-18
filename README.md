
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

## Event Triggers

- **BookingCreatedEvent**: When a member creates a booking, this event is triggered, notifying the **Notification Service** to send a confirmation, and the **Real-Time Analytics Service** to update booking statistics.
- **BookingUpdatedEvent**: When a booking is updated, this event is triggered, notifying the **Notification Service** to send an update to the member.
- **BookingCancelledEvent**: When a member cancels a booking, this event is triggered, notifying the **Notification Service** to send a cancellation notification, and the **Real-Time Analytics Service** to update the cancellation statistics.

---
## Running the Services

---

## How to Start Kafka and Zookeeper on Windows Locally

1. **Download Kafka**:  
   Download the latest Kafka binaries from [here](https://kafka.apache.org/downloads) and Create a new file in your C: drive called kafka. Extract the ZIP file to this new folder.
2. **Start Zookeeper**:  
   Open a new command prompt and navigate to the Kafka directory. Run the following command to start Zookeeper:
   ```bash
   C:\kafka\kafka_2.13-3.8.0\bin\windows\zookeeper-server-start.bat C:\kafka\kafka_2.13-3.8.0\config\zookeeper.properties
   ```
3. **Start Kafka Server**:
    Open a new command prompt and navigate to the Kafka directory. Run the following command to start the Kafka server:
    ```bash
    C:\kafka\kafka_2.13-3.8.0\bin\windows\kafka-server-start.bat C:\kafka\kafka_2.13-3.8.0\config\server.properties
    ```
4. **Create Kafka Topics**:
    Open a new command prompt and navigate to the Kafka directory. Run the following command to create the required Kafka topics:
    ```bash
    C:\kafka\kafka_2.13-3.8.0\bin\windows\kafka-topics.bat --create --topic booking-created --bootstrap-server localhost:9092
    C:\kafka\kafka_2.13-3.8.0\bin\windows\kafka-topics.bat --create --topic booking-updated --bootstrap-server localhost:9092
    C:\kafka\kafka_2.13-3.8.0\bin\windows\kafka-topics.bat --create --topic booking-cancelled --bootstrap-server localhost:9092
    ```
5. **Verify Topics**
    To verify that the topics have been created successfully, run the following command:
    ```bash
    C:\kafka\kafka_2.13-3.8.0\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
    ```
6. **Start the Services**:
    Start the services as described in the next section.

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

- **Scenario 1**: Create a new workout class.
- **Scenario 2**: Register a new member.
- **Scenario 3**: Book a workout class.
- **Scenario 4**: Update a booking.
- **Scenario 5**: Cancel a booking (delete).
- **Scenario 6**: View all bookings.
- **Scenario 7**: View all members.
- **Scenario 8**: View all workout classes.
- **Scenario 9**: View all notifications (booking events).
- **Scenario 10**: View real-time analytics (total bookings created and cancelled).
- **Scenario 11**: Delete a workout class.
- **Scenario 12**: Delete a member.
- **Scenario 13**: Delete a booking.
- **Scenario 14**: Create multiple bookings in bulk.
- **Scenario 15**: View bookings within a date range.
- **Scenario 16**: View a specific member.
- **Scenario 17**: View a specific workout class.
- **Scenario 18**: View a specific booking.
- **Scenario 19**: Update a workout class.
- **Scenario 20**: Update a member.
- **Scenario 21**: Update a booking.
- **Scenario 22**: View bookings created by a specific member.
- **Scenario 23**: View bookings for a specific workout class.
- **Scenario 24**: View bookings for a specific member.
- **Scenario 25**: View bookings for a specific workout class.
---

## ```Constraints```

- ``` Booking Service:``` A booking cannot be created without a valid workout class.
- ```Notification Service```: A notification cannot be sent without a valid booking.
- ```Real-Time Analytics Service:``` Real-time analytics cannot be updated without a valid booking event.
- ```Workout Class Service:``` A workout class cannot be created without a valid instructor.
- ```Member Service:``` A member cannot be created without a valid details.
- ```Booking Service:``` A booking cannot be created without a valid member.
- ```Booking Service:``` A booking cannot be created without a valid workout class.

---
> # cURL commands
---

# Workout Classes

## Create one workout class
```bash
curl -X POST "http://localhost:8083/api/workoutclasses" -H "Content-Type: application/json" -d "{\"id\":1,\"className\":\"Surf Fitness\",\"instructor\":\"Kelly Slater\",\"description\":\"A class focused on building the endurance and strength needed for surfing.\"}"
```
```bash
curl -X POST "http://localhost:8083/api/workoutclasses" -H "Content-Type: application/json" -d "{\"id\":2,\"className\":\"Flexibility and Balance\",\"instructor\":\"Stephanie Gilmore\",\"description\":\"Improve your flexibility and balance to master surfing with fluidity.\"}"
```
```bash
curl -X POST "http://localhost:8083/api/workoutclasses" -H "Content-Type: application/json" -d "{\"id\":3,\"className\":\"Power Surfing\",\"instructor\":\"Gabriel Medina\",\"description\":\"A high-intensity workout focused on building the power and explosiveness needed for big wave surfing.\"}"
```
```bash
curl -X POST "http://localhost:8083/api/workoutclasses" -H "Content-Type: application/json" -d "{\"id\":4,\"className\":\"Ocean Strength\",\"instructor\":\"John John Florence\",\"description\":\"Strength training for surfers, focusing on endurance and adaptability in challenging ocean conditions.\"}"
```
## Read all workout classes
```bash
curl -X GET "http://localhost:8083/api/workoutclasses"
```
## Read one workout class
```bash
curl -X GET "http://localhost:8083/api/workoutclasses/1"
```

# Members

## Create one member
```bash
curl -X POST "http://localhost:8081/api/members" -H "Content-Type: application/json" -d "{\"id\":1,\"firstName\":\"Kelly\",\"lastName\":\"Slater\",\"email\":\"kelly.slater@surfline.com\",\"phoneNumber\":\"+61 2 9012 3456\"}"
```
```bash
curl -X POST "http://localhost:8081/api/members" -H "Content-Type: application/json" -d "{\"id\":2,\"firstName\":\"Stephanie\",\"lastName\":\"Gilmore\",\"email\":\"stephanie.gilmore@surfline.com\",\"phoneNumber\":\"+61 2 9012 3456\"}"
```
```bash
curl -X POST "http://localhost:8081/api/members" -H "Content-Type: application/json" -d "{\"id\":3,\"firstName\":\"Gabriel\",\"lastName\":\"Medina\",\"email\":\"gabriel.medina@surfline.com\",\"phoneNumber\":\"+61 2 9012 3456\"}"
```
```bash
curl -X POST "http://localhost:8081/api/members" -H "Content-Type: application/json" -d "{\"id\":4,\"firstName\":\"John\",\"lastName\":\"Florence\",\"email\":\"john.florence@surfline.com\",\"phoneNumber\":\"+61 2 9012 3456\"}"
```
## Read all members
```bash
curl -X GET "http://localhost:8081/api/members"
```
## Read one member
```bash
curl -X GET "http://localhost:8081/api/members/1"
```


# Bookings
## Create one booking
```bash
curl -X POST "http://localhost:8082/api/bookings" -H "Content-Type: application/json" -d "{\"id\": 1, \"member\": {\"id\": 1}, \"workoutClass\": {\"id\": 1}}"
```
```bash
curl -X POST "http://localhost:8082/api/bookings" -H "Content-Type: application/json" -d "{\"id\": 2, \"member\": {\"id\": 2}, \"workoutClass\": {\"id\": 2}}"
```
```bash
curl -X POST "http://localhost:8082/api/bookings" -H "Content-Type: application/json" -d "{\"id\": 3, \"member\": {\"id\": 3}, \"workoutClass\": {\"id\": 3}}"
```
```bash
curl -X POST "http://localhost:8082/api/bookings" -H "Content-Type: application/json" -d "{\"id\": 4, \"member\": {\"id\": 4}, \"workoutClass\": {\"id\": 4}}"
```
## Create bulk bookings (4 total)
```bash
curl -X POST "http://localhost:8082/api/bookings/bulk" -H "Content-Type: application/json" -d "[{\"id\": 1, \"member\": {\"id\": 1}, \"workoutClass\": {\"id\": 1}}, {\"id\": 2, \"member\": {\"id\": 2}, \"workoutClass\": {\"id\": 2}}, {\"id\": 3, \"member\": {\"id\": 3}, \"workoutClass\": {\"id\": 3}}, {\"id\": 4, \"member\": {\"id\": 4}, \"workoutClass\": {\"id\": 4}}]"
```

## Read bookings in range (end date must be > today's date)
```bash
curl -i -H "Content-Type: application/json" -X GET "http://localhost:8082/api/bookings/range?start=2024-09-09T00:00:00&end=2024-10-30T23:59:59"
```
## Read all bookings
```bash
curl -i -H "Content-Type: application/json" -X GET "http://localhost:8082/api/bookings"
```
## Read one booking
```bash
curl -i -H "Content-Type: application/json" -X GET "http://localhost:8082/api/bookings/1"
```
## Update one booking
```bash
curl -X PUT "http://localhost:8082/api/bookings/1" -H "Content-Type: application/json" -d "{\"member\": {\"id\": 1}, \"workoutClass\": {\"id\": 1}, \"bookingTime\": \"2024-10-16T14:59:00\"}"
```
# Notifications
## Read all notifications - reads all the use booking created, updated, and cancelled events
```bash
curl -X GET "http://localhost:8084/api/notifications"
```

# Real-Time Analytics
## Read total bookings created
```bash
curl -X GET "http://localhost:8085/bookings/created"
```
## Read total bookings cancelled
```bash
curl -X GET "http://localhost:8085/bookings/cancelled"
```



---
> #### The *Delete*  Use Cases
> Note: This is last due to the database auto-incrementation. Otherwise you will need to make sure the IDs are correct or the specific object is found / created, and then update the ID in the delete command or on the specific object.
> Otherwise you will get an exception. `booking: {x}, {entity} Class with ID {x} not found. A booking cannot be created without a valid {entity} Class.` 

## Delete one workout class
```bash
curl -X DELETE "http://localhost:8083/api/workoutclasses/1"
```
## Delete one member
```bash
curl -X DELETE "http://localhost:8081/api/members/1"
```
## Delete one booking - Note: This will trigger a notification event and update the real-time analytics
```bash
curl -X DELETE "http://localhost:8082/api/bookings/1"
```

## Configuration

Each service has an `application.properties` file located in `src/main/resources/` where you can configure service-specific properties like ports, database settings, and Kafka server details.

---

## Contributors
- Tony Li - 7576663
- David Stevens - 7627920
- Justin Landrigan - 7471567
- Luke Moorhouse - 7603599
