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
    birth_date DATE NOT NULL,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES role(id)
);

-- If you want to match the txt, you may need to rename 'user' to 'user_' in all FKs, but for SQL standard, keep as 'user'.


CREATE TABLE adherent_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    default_quote INT,
    default_duration INT
);

CREATE TABLE adherent (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    adherent_type_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (adherent_type_id) REFERENCES adherent_type(id)
);

-- If you want to add createdAt (as in the txt and views):
-- ALTER TABLE adherent ADD COLUMN createdAt DATE;

CREATE TABLE book (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    age_min INT,
    state VARCHAR(30)
);

CREATE TABLE subscription (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherent_id INT NOT NULL,
    from_date DATE NOT NULL,
    to_date DATE NOT NULL,
    FOREIGN KEY (adherent_id) REFERENCES adherent(id)
);

CREATE TABLE reservation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherent_id INT NOT NULL,
    book_id INT NOT NULL,
    state VARCHAR(30),
    reservation_date DATE NOT NULL,
    validated_by INT,
    FOREIGN KEY (validated_by) REFERENCES user(id),
    FOREIGN KEY (adherent_id) REFERENCES adherent(id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);

CREATE TABLE loan (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherent_id INT NOT NULL,
    book_id INT NOT NULL,
    from_date DATE NOT NULL,
    to_date DATE NOT NULL,
    state VARCHAR(30),
    created_by INT,
    FOREIGN KEY (adherent_id) REFERENCES adherent(id),
    FOREIGN KEY (book_id) REFERENCES book(id),
    FOREIGN KEY (created_by) REFERENCES user(id)
);

CREATE TABLE extension_request (
    id INT PRIMARY KEY AUTO_INCREMENT,
    loan_id INT NOT NULL,
    request_date DATE NOT NULL,
    state VARCHAR(30) NOT NULL,
    reason VARCHAR(255),
    validated_by INT,
    FOREIGN KEY (validated_by) REFERENCES user(id),
    FOREIGN KEY (loan_id) REFERENCES loan(id)
);

CREATE TABLE extension (
    id INT PRIMARY KEY AUTO_INCREMENT,
    loan_id INT NOT NULL,
    duration INT NOT NULL,
    from_date DATE NOT NULL,
    to_date DATE NOT NULL,
    state VARCHAR(30),
    FOREIGN KEY (loan_id) REFERENCES loan(id)
);

CREATE TABLE return_book (
    id INT PRIMARY KEY AUTO_INCREMENT,
    loan_id INT NOT NULL,
    return_date DATE NOT NULL,
    adherent_id INT NOT NULL,
    book_id INT NOT NULL,
    state VARCHAR(30),
    FOREIGN KEY (loan_id) REFERENCES loan(id),
    FOREIGN KEY (adherent_id) REFERENCES adherent(id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);

CREATE TABLE sanction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherent_id INT NOT NULL,
    created_by INT,
    from_date DATE NOT NULL,
    to_date DATE NOT NULL,
    duration INT NOT NULL,
    FOREIGN KEY (adherent_id) REFERENCES adherent(id),
    FOREIGN KEY (created_by) REFERENCES user(id)
);

CREATE TABLE config (
    id INT PRIMARY KEY AUTO_INCREMENT,
    max_extension INT
);

-- Table to store adherent's available quote and duration
CREATE TABLE adherent_info (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherent_id INT NOT NULL,
    available_quote INT NOT NULL,
    available_duration INT NOT NULL,
    FOREIGN KEY (adherent_id) REFERENCES adherent(id)
);

CREATE TABLE HolidayList (
    id INT PRIMARY KEY AUTO_INCREMENT,
    holiday_date DATE NOT NULL,
    description VARCHAR(255)
);
