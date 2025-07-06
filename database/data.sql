
-- Roles
INSERT INTO role (name) VALUES 
('Librarian'),
('Adherent');

-- Adherent Types
INSERT INTO adherentType (name, defaultQuote, defaultDuration) VALUES 
('Student', 5, 30),
('Teacher', 10, 60),
('Anonymous', 2, 15),
('Other', 3, 20);

-- Users

-- Users with birthDate
INSERT INTO user (username, password, birthDate, roleId) VALUES 
('librarian1', 'password1', '1980-01-01', 1),
('john.doe', 'password2', '2010-05-15', 2),   -- Student, 15 years old
('jane.smith', 'password3', '1985-03-22', 2), -- Teacher, 40 years old
('paul.brown', 'password4', '2000-09-10', 2); -- Anonymous, 24 years old

-- Adherents

-- Adherents with coherent age/type
INSERT INTO adherent (userId, adherentTypeId) VALUES 
(2, 1), -- John Doe, Student (15 years)
(3, 2), -- Jane Smith, Teacher (40 years)
(4, 3); -- Paul Brown, Anonymous (24 years)

-- Books
INSERT INTO book (title, author, ageMin, state) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', 16, 'Available'),
('To Kill a Mockingbird', 'Harper Lee', 14, 'Available'),
('1984', 'George Orwell', 16, 'Available'),
('Pride and Prejudice', 'Jane Austen', 12, 'Available'),
('The Catcher in the Rye', 'J.D. Salinger', 16, 'Available'),
('The Hobbit', 'J.R.R. Tolkien', 10, 'Available'),
('Moby Dick', 'Herman Melville', 16, 'Available'),
('War and Peace', 'Leo Tolstoy', 18, 'Available'),
('The Odyssey', 'Homer', 12, 'Available'),
('Crime and Punishment', 'Fyodor Dostoevsky', 18, 'Available');
