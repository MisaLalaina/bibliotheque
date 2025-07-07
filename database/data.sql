
-- Roles

INSERT INTO role (name) VALUES 
('Librarian'),
('Adherent');

-- Adherent Types

INSERT INTO adherent_type (name, default_quote, default_duration) VALUES 
('Student', 5, 30),
('Teacher', 10, 60),
('Anonymous', 2, 15),
('Other', 3, 20);

-- Users

-- Users with birthDate

INSERT INTO user (username, password, birth_date, role_id) VALUES 
('librarian1', 'password1', '1980-01-01', 1),
('john.doe', 'password2', '2010-05-15', 2),   -- Student, 15 years old
('jane.smith', 'password3', '1985-03-22', 2), -- Teacher, 40 years old
('paul.brown', 'password4', '2000-09-10', 2); -- Anonymous, 24 years old

-- Adherents

-- Adherents with coherent age/type

INSERT INTO adherent (user_id, adherent_type_id) VALUES 
(2, 1), -- John Doe, Student (15 years)
(3, 2), -- Jane Smith, Teacher (40 years)
(4, 3); -- Paul Brown, Anonymous (24 years)

-- Books

INSERT INTO book (title, author, age_min, state) VALUES
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

INSERT INTO book_copy (book_id, copy_number, acquisition_date, copy_condition, state) VALUES
-- The Great Gatsby (3 copies)
(1, 1, '2023-01-01', 'new', 'available'),
(1, 2, '2023-02-01', 'used', 'borrowed'),
(1, 3, '2023-03-01', 'damaged', 'repair'),

-- To Kill a Mockingbird (2 copies)
(2, 1, '2022-10-10', 'used', 'available'),
(2, 2, '2022-11-15', 'used', 'available'),

-- 1984 (1 copy)
(3, 1, '2024-01-20', 'new', 'available'),

-- Pride and Prejudice (2 copies)
(4, 1, '2021-06-01', 'used', 'available'),
(4, 2, '2022-07-01', 'damaged', 'lost'),

-- The Catcher in the Rye (1 copy)
(5, 1, '2023-08-01', 'new', 'borrowed'),

-- The Hobbit (3 copies)
(6, 1, '2022-01-01', 'used', 'available'),
(6, 2, '2022-03-01', 'new', 'available'),
(6, 3, '2022-06-01', 'used', 'reserved'),

-- Moby Dick (1 copy)
(7, 1, '2024-01-10', 'new', 'available'),

-- War and Peace (2 copies)
(8, 1, '2021-12-12', 'used', 'available'),
(8, 2, '2022-02-02', 'used', 'available'),

-- The Odyssey (2 copies)
(9, 1, '2023-04-04', 'used', 'available'),
(9, 2, '2023-05-05', 'used', 'borrowed'),

-- Crime and Punishment (1 copy)
(10, 1, '2023-09-01', 'new', 'available');
