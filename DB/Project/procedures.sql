DROP PROCEDURE insert_user;
DROP PROCEDURE delete_signature;
DROP PROCEDURE update_payment_order;
DROP PROCEDURE delete_payment_order;
DROP PROCEDURE insert_transaction;
DROP PROCEDURE update_transaction;
DROP PROCEDURE delete_transaction;
DROP PROCEDURE delete_account;


DELIMITER $$
CREATE PROCEDURE insert_user(p_ssn VARCHAR(10), p_firstname VARCHAR(10), p_lastname VARCHAR(10), p_customer_id INT, p_password VARCHAR(256), p_number VARCHAR(11), p_address VARCHAR(256))
    BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION 
        BEGIN
            SIGNAL sqlstate '45001' set message_text = "Error while inserting Account. Check all fields are valid and phone or number is not null.";
            ROLLBACK;
        END;
        IF ((p_number = null) OR (p_address = null)) THEN 
            SIGNAL sqlstate '45001' set message_text = "Null values!";
        END IF;

        START TRANSACTION;
            INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES (p_ssn, p_firstname, p_lastname, p_customer_id, p_password);
            INSERT INTO PhoneNumber(p_ssn, number) VALUES (p_ssn, p_number);
            INSERT INTO Address(ssn, address) VALUES (p_ssn, p_address);
        COMMIT; 
    END;$$


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
            SIGNAL sqlstate '45001' set message_text = "Error while deleting PaymentOrder. Check authority and other things.";
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

CREATE PROCEDURE delete_transaction(p_customer VARCHAR(10), p_payment_order INT, p_destination INT)
    BEGIN
        IF (p_customer <> (
            SELECT creator
            FROM PaymentOrder
            WHERE PaymentOrder.ID = p_payment_order
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "Only creator of this PaymentOrder can delete Transaction.";
        ELSE
            DELETE FROM Transaction
            WHERE payment_order = p_payment_order AND destination = p_destination;
        END IF;
    END;$$


CREATE PROCEDURE delete_account(p_ID INT)
    BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION 
        BEGIN
            SIGNAL sqlstate '45001' set message_text = "Error while deleting Account. Maybe some PaymentOrders are signed.";
            ROLLBACK;
        END;

        START TRANSACTION;
            
            DELETE FROM AccountOwner 
                WHERE AccountOwner.account = p_ID;
            
            DELETE FROM SignatureAccess 
                WHERE SignatureAccess.account = p_ID;
            
            DELETE FROM AcceptAccess 
                WHERE AcceptAccess.account = p_ID;
            
            DELETE FROM ViewAccountAccess
                WHERE ViewAccountAccess.account = p_ID;
            
            DELETE FROM Settings 
                WHERE Settings.account = p_ID;
            
            DELETE FROM Bill 
                WHERE Bill.account = p_ID;
                        
            DELETE FROM PaymentOrder
                WHERE PaymentOrder.account = p_ID;

            DELETE FROM Account
                WHERE Account.ID = p_ID;

        COMMIT;
    END;$$


 DELIMITER ;