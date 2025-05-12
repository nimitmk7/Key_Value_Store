# SETUP DB

```
1. `brew install mysql`
2. `brew services start mysql`
3. `mysql -u root`
4. `CREATE USER <USERNAME>@'localhost' IDENTIFIED BY <PASSWORD>;`
5. `GRANT ALL PRIVILEGES ON mydb.* TO '<username>'@'localhost'`
6. `FLUSH PRIVILIGES;`
```

# Run the server
mvn clean spring-boot:run

# How to call the APIs

## Get Value for Key

```
curl --location --request GET 'http://localhost:8080/api/kv/' \
--header 'Content-Type: application/json' \
--data '{"key": <key>}'
```

## Set Value for Key

```
curl --location --request PUT 'http://localhost:8080/api/kv/' \
--header 'Content-Type: application/json' \
--data '{"key": <key> , "value": <value>, "expiry": <expiry_in_minutes>}'
```

## Delete Key

```
curl --location --request DELETE 'http://localhost:8080/api/kv/' \
--header 'Content-Type: application/json' \
--data '{
    "key": <key>
}'
```
