

## SETUP DB
1. `brew install mysql`
2. `brew services start mysql`
3. `mysql -u root`
4. `CREATE USER <USERNAME>@'localhost' IDENTIFIED BY <PASSWORD>;`
5. `GRANT ALL PRIVILEGES ON mydb.* TO '<username>'@'localhost'`
6. `FLUSH PRIVILIGES;`

## 
