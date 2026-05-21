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
