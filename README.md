# JavaCRUDConsoleApp
A simple Java-based console application implementing basic CRUD operations with MySQL, utilizing JDBC for database connectivity.

## Features
- **Create** new records in the database.
- **Read** and display existing records.
- **Update** existing records.
- **Delete** records from the database.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or later
- MySQL Server
- MySQL Connector/J 9.0.0 (latest)
- IDE or text editor of your choice

### Database Setup
1. Create a MySQL database named `crud`.
2. Create a table named `crud_table` with the following structure:
   ```sql
   CREATE TABLE crud_table (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL,
    age INT (255) NOT NULL,
    section VARCHAR (255) NOT NULL
   );
