Absolutely! Here’s the full, copy-paste-ready README.md in proper Markdown format for your GitHub repo:

⸻


# 🗄️ KV Store - Spring Boot Key-Value Store

A simple key-value store with TTL support, built using Spring Boot and MySQL. Supports setting, retrieving, and soft-deleting keys with automatic expiration.

---

## 🚀 Setup Instructions

### ✅ 1. Install and Start MySQL (macOS with Homebrew)

```bash
brew install mysql
brew services start mysql
```

### ✅ 2. Configure MySQL User and Database

```bash
mysql -u root
CREATE DATABASE kv_store;
CREATE USER 'your_username'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON kv_store.* TO 'your_username'@'localhost';
FLUSH PRIVILEGES;
```

⸻

### 🛠️ 3. Running the Server

```bash
mvn clean spring-boot:run
```

Make sure application.properties or db.properties is properly configured with your DB credentials.

⸻

## 📡 API Endpoints

Base URL: http://localhost:8080/api/kv/

⸻

### 🔍 Get Value for Key

```bash
curl -X GET http://localhost:8080/api/kv/ \
     -H "Content-Type: application/json" \
     -d '{"key": "myKey"}'
```


⸻

### 📝 Set Value for Key

```bash
curl -X PUT http://localhost:8080/api/kv/ \
     -H "Content-Type: application/json" \
     -d '{"key": "myKey", "value": "myValue", "expiry": "expiry_in_minutes"}'
```

⸻

### ❌ Delete Key (Soft Delete)

```bash
curl -X DELETE http://localhost:8080/api/kv/ \
     -H "Content-Type: application/json" \
     -d '{"key": "myKey"}'
```


⸻

### ⏰ Expiration Behavior
	•	Keys auto-expire after the specified TTL (expiry in minutes).
	•	A scheduled CRON job runs every 3 minutes to purge expired keys.
	•	Soft-deleted keys are manually expired and can be differentiated from auto-expired ones.

⸻

### 📦 Tech Stack
	•	Java 17
	•	Spring Boot
	•	MySQL
	•	Flyway (for DB migrations)
	•	Hibernate (JPA)
	•	Maven
