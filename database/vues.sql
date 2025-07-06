

-- View to see all users linked to adherents
CREATE OR REPLACE VIEW v_adherentUser AS
SELECT 
    a.id AS id,
    u.id AS userId,
    u.username,
    u.birthDate,
    r.name AS role,
    at.name AS adherent_type
FROM 
    adherent a
JOIN 
    user u ON a.userId = u.id
JOIN 
    role r ON u.roleId = r.id
JOIN 
    adherentType at ON a.adherentTypeId = at.id;