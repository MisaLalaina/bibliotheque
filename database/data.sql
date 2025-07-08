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
(2, 'ETU001', 'pass', '2007-02-01', 2),
(3, 'ETU002', 'pass', '2006-02-01', 2),
(4, 'ETU003', 'pass', '2005-04-01', 2),
(5, 'ENS001', 'pass', '1980-07-01', 2),
(6, 'ENS002', 'pass', '1979-08-01', 2),
(7, 'ENS003', 'pass', '1981-07-01', 2),
(8, 'PROF001', 'pass', '1985-06-01', 2),
(9, 'PROF002', 'pass', '1980-10-01', 2);

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
(1, 'Les Misérables', 'Victor Hugo', 12, 'Disponible'),
(2, 'L''Étranger', 'Albert Camus', 14, 'Disponible'),
(3, 'Harry Potter à l''école des sorciers', 'J.K. Rowling', 10, 'Disponible');

-- BOOK COPIES
INSERT INTO book_copy (book_id, copy_number, acquisition_date, copy_condition, state) VALUES
(1, "MIS001", '2023-01-01', 'Neuf', 'Disponible'),
(1, "MIS002", '2023-01-01', 'Neuf', 'Disponible'),
(1, "MIS003", '2023-01-01', 'Neuf', 'Disponible'),
(2, "ETR001", '2023-01-01', 'Neuf', 'Disponible'),
(2, "ETR002", '2023-01-01', 'Neuf', 'Disponible'),
(3, "HAR001", '2023-01-01', 'Neuf', 'Disponible');

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
INSERT INTO adherent_info (adherent_id, available_pret,available_duration, available_reservation, available_extension) VALUES
(1, 2, 5, 1, 1),
(2, 2, 5, 1, 1),
(3, 2, 5, 1, 1),
(4, 3, 10, 2, 2),
(5, 3, 10, 2, 2),
(6, 3, 10, 2, 2),
(7, 4, 12, 3, 3),
(8, 4, 12, 3, 3);