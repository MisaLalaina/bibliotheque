DROP DATABASE IF EXISTS bibliotheque;
CREATE DATABASE bibliotheque;
USE bibliotheque;
-- Library Management System Schema (camelCase, English)

CREATE TABLE role (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    birthDate DATE NOT NULL,
    roleId INT,
    FOREIGN KEY (roleId) REFERENCES role(id)
);

-- If you want to match the txt, you may need to rename 'user' to 'user_' in all FKs, but for SQL standard, keep as 'user'.

CREATE TABLE adherentType (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    defaultQuote INT,
    defaultDuration INT
);

CREATE TABLE adherent (
    id INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    adherentTypeId INT NOT NULL,
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (adherentTypeId) REFERENCES adherentType(id)
);

-- If you want to add createdAt (as in the txt and views):
-- ALTER TABLE adherent ADD COLUMN createdAt DATE;

CREATE TABLE book (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    ageMin INT,
    state VARCHAR(30)
);

CREATE TABLE subscription (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherentId INT NOT NULL,
    fromDate DATE NOT NULL,
    toDate DATE NOT NULL,
    FOREIGN KEY (adherentId) REFERENCES adherent(id)
);

CREATE TABLE reservation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherentId INT NOT NULL,
    bookId INT NOT NULL,
    state VARCHAR(30),
    reservationDate DATE NOT NULL,
    validatedBy INT,
    FOREIGN KEY (validatedBy) REFERENCES user(id),
    FOREIGN KEY (adherentId) REFERENCES adherent(id),
    FOREIGN KEY (bookId) REFERENCES book(id)
);

CREATE TABLE loan (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherentId INT NOT NULL,
    bookId INT NOT NULL,
    fromDate DATE NOT NULL,
    toDate DATE NOT NULL,
    createdBy INT,
    FOREIGN KEY (adherentId) REFERENCES adherent(id),
    FOREIGN KEY (bookId) REFERENCES book(id),
    FOREIGN KEY (createdBy) REFERENCES user(id)
);

CREATE TABLE extensionRequest (
    id INT PRIMARY KEY AUTO_INCREMENT,
    loanId INT NOT NULL,
    requestDate DATE NOT NULL,
    state VARCHAR(30) NOT NULL,
    reason VARCHAR(255),
    validatedBy INT,
    FOREIGN KEY (validatedBy) REFERENCES user(id),
    FOREIGN KEY (loanId) REFERENCES loan(id)
);

CREATE TABLE extension (
    id INT PRIMARY KEY AUTO_INCREMENT,
    loanId INT NOT NULL,
    duration INT NOT NULL,
    fromDate DATE NOT NULL,
    toDate DATE NOT NULL,
    FOREIGN KEY (loanId) REFERENCES loan(id)
);

CREATE TABLE returnBook (
    id INT PRIMARY KEY AUTO_INCREMENT,
    loanId INT NOT NULL,
    returnDate DATE NOT NULL,
    adherentId INT NOT NULL,
    bookId INT NOT NULL,
    FOREIGN KEY (loanId) REFERENCES loan(id),
    FOREIGN KEY (adherentId) REFERENCES adherent(id),
    FOREIGN KEY (bookId) REFERENCES book(id)
);

CREATE TABLE sanction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherentId INT NOT NULL,
    createdBy INT,
    fromDate DATE NOT NULL,
    toDate DATE NOT NULL,
    duration INT NOT NULL,
    FOREIGN KEY (adherentId) REFERENCES adherent(id),
    FOREIGN KEY (createdBy) REFERENCES user(id)
);

CREATE TABLE config (
    id INT PRIMARY KEY AUTO_INCREMENT,
    maxExtension INT
);

-- Table to store adherent's available quote and duration
CREATE TABLE adherentInfo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherentId INT NOT NULL,
    availableQuote INT NOT NULL,
    availableDuration INT NOT NULL,
    FOREIGN KEY (adherentId) REFERENCES adherent(id)
);
