-- View for Book and Reservation
CREATE OR REPLACE VIEW v_book_reservation AS
SELECT 
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
FROM book b
LEFT JOIN reservation r ON b.id = r.book_id;

-- View for Book and Loan
CREATE OR REPLACE VIEW v_book_loan AS
SELECT 
    b.id AS book_id,
    b.title AS book_title,
    b.author AS book_author,
    b.age_min AS book_age_min,
    b.state AS book_state,
    l.id AS loan_id,
    l.adherent_id,
    l.state AS loan_state,
    l.from_date,
    l.to_date,
    l.created_by
FROM book b
LEFT JOIN loan l ON b.id = l.book_id;
