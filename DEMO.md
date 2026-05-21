# Running The Project

## Prerequisites

- Java 21
- Maven
- Go
- Docker Desktop

---

# Step 1 — Start Infrastructure

```bash
docker compose up -d
```

This starts:
- Kafka
- Zookeeper
- Redis
- MongoDB

---

# Step 2 — Start Java Service

```bash
cd sms-sender-java

mvn spring-boot:run
```

Java service runs on:

```text
http://localhost:8080
```

---

# Step 3 — Start Go Service

```bash
cd sms-store-go

go run main.go
```

Go service runs on:

```text
http://localhost:8081
```

---

# Unit Tests

## Java Tests

```bash
mvn test
```

## Go Tests

```bash
go test ./...
```

---

# Kafka Event Example

```json
{
  "userId": "u123",
  "phoneNumber": "9999999999",
  "message": "Kafka verification",
  "status": "SUCCESS"
}
```

---

# Future Improvements

- Retry mechanism
- Dead Letter Queue (DLQ)
- Dockerized application services
- Swagger/OpenAPI documentation
- Kubernetes deployment
- JWT authentication
- Metrics and monitoring

---

# Learning Outcomes

This project demonstrates:
- distributed systems design
- event-driven architecture
- asynchronous communication
- Kafka producer/consumer model
- polyglot microservices
- Redis caching
- MongoDB persistence
- service decoupling
- unit testing
```
:::

---

# 2. DEMONSTRATION STEPS

Now create another file:

```text id="0jlwmo"
DEMO.md
```

This is specifically for assignment demonstration.

---

# CONTENT

:::writing{variant="standard" id="58392"}
# Demonstration Steps

## 1. Start Infrastructure

```bash
docker compose up -d
```

Verify containers:

```bash
docker ps
```

Expected containers:
- kafka
- zookeeper
- redis-sms
- mongodb

---

# 2. Start Java SMS Sender Service

```bash
cd sms-sender-java

mvn spring-boot:run
```

Expected:
- Spring Boot starts on port 8080

---

# 3. Start Go SMS Store Service

```bash
cd sms-store-go

go run main.go
```

Expected logs:

```text
Connected to MongoDB
Kafka consumer started
```

---

# 4. Send SMS Request

```bash
curl -X POST http://localhost:8080/v1/sms/send \
-H "Content-Type: application/json" \
-d '{
  "userId":"u123",
  "phoneNumber":"9999999999",
  "message":"Distributed systems test"
}'
```

Expected response:

```json
{
  "status":"SUCCESS",
  "message":"SMS sent successfully"
}
```

---

# 5. Observe Go Consumer Logs

Expected logs:

```text
Received Kafka Event
SMS event stored in MongoDB
```

This confirms:
- Kafka event publishing
- Kafka event consumption
- MongoDB persistence

---

# 6. Retrieve SMS History

```bash
curl http://localhost:8081/v1/sms/history/u123
```

Expected response:

```json
[
  {
    "userId":"u123",
    "phoneNumber":"9999999999",
    "message":"Distributed systems test",
    "status":"SUCCESS"
  }
]
```

This confirms:
- successful MongoDB retrieval
- end-to-end distributed flow
```
:::

---
