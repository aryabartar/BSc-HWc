DROP PROCEDURE delete_signature;


DELIMITER $$

CREATE PROCEDURE delete_signature(p_customer VARCHAR(10), p_payment_order INT)
    BEGIN
        DELETE FROM Signature 
        WHERE customer = p_customer AND payment_order = p_payment_order;
    END;$$


 DELIMITER ;