DROP TRIGGER insert_to_payment_order;


DELIMITER $$

CREATE TRIGGER insert_to_payment_order AFTER INSERT 
    ON PaymentOrder
    FOR EACH ROW
    BEGIN
        IF (NOT EXISTS(
            SELECT *
            FROM AccountOwner
            WHERE customer = NEW.creator and account = NEW.account
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "This user has no permission to insert a PaymentOrder for this account.";
        END IF;
    END;$$

DELIMITER ;