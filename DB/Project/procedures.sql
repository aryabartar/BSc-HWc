DROP PROCEDURE delete_signature;
DROP PROCEDURE update_payment_order;
DROP PROCEDURE delete_payment_order;
DROP PROCEDURE insert_transaction;
DROP PROCEDURE update_transaction;


DELIMITER $$

CREATE PROCEDURE delete_signature(p_customer VARCHAR(10), p_payment_order INT)
    BEGIN
        DELETE FROM Signature 
        WHERE customer = p_customer AND payment_order = p_payment_order;
    END;$$


CREATE PROCEDURE update_payment_order(p_customer VARCHAR(10), p_ID INT, p_note VARCHAR(1024))
    BEGIN
        IF (p_customer <> (
            SELECT creator
            FROM PaymentOrder 
            WHERE PaymentOrder.ID = p_ID
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "You don't have authority to change this PaymentOrder.";
        ELSE 
            UPDATE PaymentOrder 
            SET note = p_note
            WHERE ID = p_ID;
        END IF;  

    END;$$

CREATE PROCEDURE delete_payment_order(p_customer VARCHAR(10), p_ID INT)
    BEGIN

        DECLARE EXIT HANDLER FOR SQLEXCEPTION 
        BEGIN
            ROLLBACK;
        END;

        IF (p_customer <> (
            SELECT creator
            FROM PaymentOrder 
            WHERE PaymentOrder.ID = p_ID
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "You don't have authority to delete this PaymentOrder.";
        ELSE

            START TRANSACTION;
                
                DELETE FROM AcceptPayment
                    WHERE AcceptPayment.payment_order = p_ID;
                DELETE FROM Signature 
                    WHERE Signature.payment_order = p_ID;
                DELETE FROM Transaction
                    WHERE Transaction.payment_order = p_ID;
                DELETE FROM PaymentOrder 
                    WHERE PaymentOrder.ID = p_ID;

            COMMIT;
        END IF;  

    END;$$

CREATE PROCEDURE insert_transaction(p_customer VARCHAR(10), p_payment_order INT, p_destination INT, p_amount NUMERIC(10,0))
    BEGIN
        IF (p_customer <> (
            SELECT creator
            FROM PaymentOrder
            WHERE PaymentOrder.ID = p_payment_order
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "Only creator of this PaymentOrder can add Transaction.";
        ELSE
            INSERT INTO Transaction(payment_order, destination, amount) VALUES (p_payment_order, p_destination, p_amount);
        END IF;
    END;$$

CREATE PROCEDURE update_transaction(p_customer VARCHAR(10), p_payment_order INT, p_destination INT, p_amount NUMERIC(10,0))
    BEGIN
        IF (p_customer <> (
            SELECT creator
            FROM PaymentOrder
            WHERE PaymentOrder.ID = p_payment_order
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "Only creator of this PaymentOrder can update Transaction.";
        ELSE
            UPDATE Transaction 
            SET amount = p_amount
            WHERE payment_order = p_payment_order AND destination = p_destination;        
        END IF;
    END;$$


 DELIMITER ;