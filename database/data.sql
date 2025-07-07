-- ROLES
INSERT INTO role (name) VALUES 
('Librarian'),
('Adherent');

-- ADHERENT TYPES
INSERT INTO adherent_type (name, default_quote, default_duration) VALUES 
('Etudiant', 5, 30),
('Enseignant', 10, 60),
('Professionnel', 12, 90);

-- USERS (birth_date adjusted for age consistency)
INSERT INTO user (id, username, password, birth_date, role_id) VALUES 
(1, 'admin', 'admin', '1970-01-01', 1),
(2, 'etu001', 'pass', '2007-02-01', 2),
(3, 'etu002', 'pass', '2006-02-01', 2),
(4, 'etu003', 'pass', '2005-04-01', 2),
(5, 'ens001', 'pass', '1980-07-01', 2),
(6, 'ens002', 'pass', '1979-08-01', 2),
(7, 'ens003', 'pass', '1981-07-01', 2),
(8, 'prof001', 'pass', '1985-06-01', 2),
(9, 'prof002', 'pass', '1980-10-01', 2);

-- ADHERENTS
INSERT INTO adherent (id, user_id, adherent_type_id) VALUES 
(1, 2, 1),
(2, 3, 1),
(3, 4, 1),
(4, 5, 2),
(5, 6, 2),
(6, 7, 2),
(7, 8, 3),
(8, 9, 3);

-- BOOKS
INSERT INTO book (id, title, author, age_min, state) VALUES
(1, 'Les Misérables', 'Victor Hugo', 12, 'Available'),
(2, 'L''Étranger', 'Albert Camus', 14, 'Available'),
(3, 'Harry Potter à l''école des sorciers', 'J.K. Rowling', 10, 'Available');

-- BOOK COPIES
INSERT INTO book_copy (book_id, copy_number, acquisition_date, copy_condition, state) VALUES
(1, 1, '2023-01-01', 'Neuf', 'Available'),
(1, 2, '2023-01-01', 'Neuf', 'Available'),
(1, 3, '2023-01-01', 'Neuf', 'Available'),
(2, 1, '2023-01-01', 'Neuf', 'Available'),
(2, 2, '2023-01-01', 'Neuf', 'Available'),
(3, 1, '2023-01-01', 'Neuf', 'Available');

-- SUBSCRIPTIONS
INSERT INTO subscription (id, adherent_id, from_date, to_date) VALUES
(1, 1, '2025-02-01', '2025-12-01'),
(2, 2, '2025-02-01', '2025-07-01'),
(3, 3, '2025-04-01', '2025-12-01'),
(4, 4, '2025-07-01', '2026-07-01'),
(5, 5, '2025-08-01', '2026-05-01'),
(6, 6, '2025-07-01', '2026-06-01'),
(7, 7, '2025-06-01', '2025-12-01'),
(8, 8, '2024-10-01', '2025-06-01');

-- CONFIG
INSERT INTO config (max_extension, default_sanction) VALUES (2, 10);

-- ADHERENT INFO (available_quote, extension and duration mapped from types)
INSERT INTO adherent_info (adherent_id, available_quote, available_extension, available_duration) VALUES
(1, 5, 1, 30),
(2, 5, 1, 30),
(3, 5, 1, 30),
(4, 10, 2, 60),
(5, 10, 2, 60),
(6, 10, 2, 60),
(7, 12, 3, 90),
(8, 12, 3, 90);
