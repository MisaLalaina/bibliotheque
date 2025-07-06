


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