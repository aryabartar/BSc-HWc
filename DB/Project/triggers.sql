DROP TRIGGER insert_to_payment_order;
DROP TRIGGER update_payment_order;
DROP TRIGGER update_transaction;
DROP TRIGGER insert_transaction;
DROP TRIGGER delete_transaction;


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

CREATE TRIGGER update_payment_order AFTER UPDATE 
    ON PaymentOrder
    FOR EACH ROW
    BEGIN
        IF (EXISTS(
                SELECT * 
                FROM Signature
                WHERE Signature.payment_order = NEW.ID
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "One user had signed this PaymentOrder so you can not update it.";
        END IF;
    END;$$

CREATE TRIGGER insert_transaction AFTER INSERT
    ON Transaction
    FOR EACH ROW
    BEGIN
        IF (EXISTS(
                SELECT * 
                FROM Signature
                WHERE Signature.payment_order = NEW.payment_order
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "One user had signed this PaymentOrder so you can not add transaction to it.";
        END IF;
    END;$$

CREATE TRIGGER update_transaction AFTER UPDATE
    ON Transaction
    FOR EACH ROW
    BEGIN
        IF (EXISTS(
                SELECT * 
                FROM Signature
                WHERE Signature.payment_order = NEW.payment_order
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "One user had signed this PaymentOrder so you can not update its transaction.";
        END IF;
    END;$$

CREATE TRIGGER delete_transaction AFTER DELETE
    ON Transaction
    FOR EACH ROW
    BEGIN
        IF (EXISTS(
                SELECT * 
                FROM Signature
                WHERE Signature.payment_order = OLD.payment_order
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "One user had signed this PaymentOrder so you can not delete this transaction.";
        END IF;
    END;$$

DELIMITER ;