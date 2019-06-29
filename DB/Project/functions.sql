DROP FUNCTION get_hashed_password;

DELIMITER $$

CREATE FUNCTION get_hashed_password(essn VARCHAR(10), epassword VARCHAR(256)) RETURNS VARCHAR(256)
BEGIN
    DECLARE password_update_time TIMESTAMP; 
    DECLARE hashed_password VARCHAR(256); 
    DECLARE ehashed_password VARCHAR(256);

    SELECT C.password_update_time INTO password_update_time
    FROM Customer AS C
    WHERE ssn = essn; 

    SELECT C.password INTO hashed_password
    FROM Customer AS C
    WHERE ssn = essn; 
    
    SET ehashed_password = SHA2(CONCAT(essn, password_update_time, epassword), 256);

    IF (ehashed_password = hashed_password) THEN 
        RETURN 1; 
    ELSE 
        RETURN 0;
    END IF;

END;$$

DELIMITER ;



-- CREATE FUNCTION get_hashed_password(essn VARCHAR(10), epassword VARCHAR(256)) RETURNS INT
-- BEGIN
--     DECLARE update_time TIME; 
--     DECLARE hashed_password VARCHAR(256); 
--     DECLARE ehashed_password VARCHAR(256);

--     SELECT C.update_time INTO update_time
--     FROM Customer AS C
--     WHERE ssn = essn; 

--     SELECT C.password INTO hashed_password
--     FROM Customer AS C
--     WHERE ssn = essn; 
    
--     SET ehashed_password = SHA2(CONCAT(essn, update_time, epassword), 256);
    
--     IF (ehashed_password = hashed_password) THEN 
--         RETURN 1; 
--     ELSE 
--         RETURN 0;
--     END IF;

-- END;$$

-- DELIMITER ;
