-- View to see all users linked to adherents (snake_case)
CREATE OR REPLACE VIEW v_adherent_user AS
SELECT 
    a.id AS adherent_id,
    u.id AS user_id,
    u.username,
    u.birth_date,
    r.name AS role,
    at.name AS adherent_type
FROM 
    adherent a
JOIN 
    user u ON a.user_id = u.id
JOIN 
    role r ON u.role_id = r.id
JOIN 
    adherent_type at ON a.adherent_type_id = at.id;

CREATE VIEW view_book_copy_details AS
SELECT 
    bc.id AS copy_id,
    bc.copy_number,
    bc.acquisition_date,
    bc.copy_condition,
    bc.state AS copy_state,
    
    b.id AS book_id,
    b.title,
    b.author,
    b.age_min,
    b.state AS book_state
FROM 
    book_copy bc
JOIN 
    book b ON bc.book_id = b.id;


-- View for BookCopy and Reservation
CREATE OR REPLACE VIEW v_book_reservation AS
SELECT 
    bc.id AS copy_id,
    bc.copy_number,
    bc.acquisition_date,
    bc.copy_condition,
    bc.state AS copy_state,
    b.id AS book_id,
    b.title AS book_title,
    b.author AS book_author,
    b.age_min AS book_age_min,
    b.state AS book_state,
    r.id AS reservation_id,
    r.adherent_id,
    r.state AS reservation_state,
    r.reservation_date,
    r.validated_by
FROM book_copy bc
JOIN book b ON bc.book_id = b.id
LEFT JOIN reservation r ON bc.book_id = r.book_id;

-- View for Book and Loan
CREATE OR REPLACE VIEW v_book_loan AS
SELECT 
    b.book_id,
    b.copy_id,
    b.title AS book_title,
    b.author AS book_author,
    b.age_min AS book_age_min,
    b.copy_state,
    b.copy_number,
    l.id AS loan_id,
    l.adherent_id,
    l.state AS loan_state,
    l.from_date,
    l.to_date,
    l.created_by
FROM view_book_copy_details b
LEFT JOIN loan l ON b.copy_id = l.book_copy_id;

CREATE VIEW view_book_with_copy_stats AS
SELECT
    b.id AS book_id,
    b.title,
    b.author,
    b.age_min,
    b.state AS book_state,

    COUNT(bc.id) AS total_copies,
    SUM(CASE WHEN bc.state = 'available' THEN 1 ELSE 0 END) AS available_copies,
    SUM(CASE WHEN bc.state = 'borrowed' THEN 1 ELSE 0 END) AS borrowed_copies,
    SUM(CASE WHEN bc.state = 'reserved' THEN 1 ELSE 0 END) AS reserved_copies,
    SUM(CASE WHEN bc.state = 'lost' THEN 1 ELSE 0 END) AS lost_copies,
    SUM(CASE WHEN bc.state = 'repair' THEN 1 ELSE 0 END) AS repair_copies

FROM book b
LEFT JOIN book_copy bc ON b.id = bc.book_id
GROUP BY b.id, b.title, b.author, b.age_min, b.state;
