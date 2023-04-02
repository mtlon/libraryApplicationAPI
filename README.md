# Library Application (API)
This is an application for managing book lending. The application allows for basic login support through controlled access using encrypted authentication data stored in a database. Additionally, the application provides access to a list of available books with the option to search by name, author, and release date. The entire backend is based on `REST` and exchanges data in `JSON` format, communicating with the frontend.

## Technologies
Project is created with:
- Java 8
- Spring
- Hibernate
- MySQL
- JAX-RS
- Jakarta Persistence
- Jakarta RESTful Web Services

## Installation
To run this application, you will need: 
- [OpenJDK 18](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
- [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/)
- [Glassfish Server 6.2.5](https://projects.eclipse.org/projects/ee4j.glassfish/downloads)
- MySQL Connector - This file is located in the application folder. Path: `libraryApplication/jar`.

## Setup

  1. Copy the MySQL Connector file to `glassfish6/glassfish/lib directory`
  2. Open IntelliJ IDEA Ultimate and load the Maven project.
  3. Configure the Glassfish server by selecting the path to the Glassfish server folder.
  4. Add the `libraryApplication:war` exploded artifact.
 
 ### Configure the database
 - Start the Glassfish server
 - Go to `http://localhost:4848/` in your web browser
 - Navigate to Resources > JDBC > JDBC Connection Pools
 - Enter the Datasource Classname: `com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource`
 - Create the following additional properties:
 ```
 - url: jdbc:mysql://localhost:3306/library?useSSL=false
 - username: root
 - password: root
 ```
  - Test the database connection by clicking `Ping`
