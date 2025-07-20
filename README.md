Library Management System

A console-based Library Management System developed in Java with MySQL database integration for storing books, users, and transactions data.


> Features

1. Add new books to the library
2. View all available books
3. Add new users
4. Return books with fine calculation (₹10 per day after 7 days)
5. Issue books to users
6. Update book details (title, author, category, etc.)
7. Delete books from the library


> Technologies Used

1. Java SE 17
2. MySQL (Workbench & Server)
3. JDBC (MySQL Connector/J)
4. Git & GitHub for version control


> Database Tables

1. books – Stores book details (title, author, category, ISBN, quantity, available count)
2. users – Stores user details (name, email, contact)
3. transactions – Tracks issued and returned books with fine calculation


## Setup Instructions

 1. Clone the repository

git clone https://github.com/nspandana10/LibraryManagementSystem.git
cd LibraryManagementSystem

2. Compile all Java files

javac *.java

3. Run the program

java -cp ".;mysql-connector-j-9.3.0.jar" MainMenu
(Ensure the MySQL Connector JAR file is in your project folder.)

4.  MySQL Setup

Create a database named library_db
Create tables books, users, and transactions as per your table design
Update DBConnection.java with your MySQL username and password

> Project Structure

LibraryManagementSystem/
│
├── AddBook.java
├── ViewBooks.java
├── AddUser.java
├── IssueBook.java
├── ReturnBook.java
├── UpdateBook.java
├── DeleteBook.java
├── DBConnection.java
├── MainMenu.java
└── mysql-connector-j-9.3.0.jar

> Usage

This project is ideal for:

Java & MySQL practice
Understanding JDBC integration

