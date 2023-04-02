# Library Application (API)
This project is a library application that manages books and users.

## Technologies
Project is created with:
- Java 8
- Spring JDBC
- Jakarta Persistence
- Jakarta RESTful Web Services

## Installation
To run this application, you will need: 
- [OpenJDK 18](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
- [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/)
- [Glassfish Server 6.2.5](https://projects.eclipse.org/projects/ee4j.glassfish/downloads)
- MySQL Connector - This file is located in the application folder. Path: `libraryApplication/jar/mysql-connector-java-8.0.27.jar`.

## Setup

  1. Clone the repository ```git clone https://github.com/{username}/library-application.git```
  2. Open the project in IntelliJ IDEA.
  3. Install OpenJDK 18 and set it as the project SDK in IntelliJ IDEA.
  4. Install Glassfish Server 6.2.5 and configure it in IntelliJ IDEA.
  5. Import the MySQL Connector.
  6. Run the project.

## Usage
Book Class
The Book class represents a book in the library. It has the following properties:

id (int): The unique identifier of the book.
title (String): The title of the book.
releaseYear (int): The year when the book was released.
authorName (String): The name of the author of the book.
authorSurname (String): The surname of the author of the book.
isBorrowed (int): The status of the book (0 = not borrowed, 1 = borrowed).

## User Class
The User class represents a user of the library. It has the following properties:

id (int): The unique identifier of the user.
username (String): The username of the user.
password (String): The password of the user.

## PasswordEncryption Class
The PasswordEncryption class is used to encrypt and decrypt passwords. It has the following methods:

encryptPassword(String password): Encrypts the given password using a key.
decryptPassword(String password): Decrypts the given password using the same key.

## LibraryClient Class
The LibraryClient class is the main class of the application. It handles the RESTful API requests and responses. It has the following methods:

getUsers(): Returns a list of all users in the library.
addUser(User user): Adds a new user to the library.
authenticateUser(User user): Authenticates the given user.
deleteUser(int id): Deletes a user from the library.

## License
This project is licensed under the MIT License - see the LICENSE file for details.
