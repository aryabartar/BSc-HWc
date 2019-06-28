DROP TRIGGER insert_to_payment_order;
DROP TRIGGER update_payment_order;
DROP TRIGGER update_transaction;
DROP TRIGGER insert_transaction;
DROP TRIGGER delete_transaction;
DROP TRIGGER insert_signature;
DROP TRIGGER delete_signature;
DROP TRIGGER insert_accept_payment;
DROP TRIGGER insert_bill;


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

CREATE TRIGGER insert_signature AFTER INSERT
    ON Signature
    FOR EACH ROW
    BEGIN
        IF (NOT EXISTS(
                SELECT * 
                FROM SignatureAccess JOIN PaymentOrder ON SignatureAccess.account = PaymentOrder.account
                WHERE SignatureAccess.customer = NEW.customer AND PaymentOrder.ID = NEW.payment_order
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "No sign permission.";
        END IF;
    END;$$

CREATE TRIGGER delete_signature AFTER DELETE
    ON Signature
    FOR EACH ROW
    BEGIN
        IF (EXISTS(
                SELECT * 
                FROM PaymentOrder JOIN AcceptPayment ON PaymentOrder.ID = AcceptPayment.payment_order
                WHERE PaymentOrder.ID = OLD.payment_order
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "Can not delete signature from accepted OrderPayment";
        ELSE  
            INSERT INTO SignatureHistory(customer, payment_order, create_time) VALUES (OLD.customer, OLD.payment_order, OLD.create_time);
        END IF;
    END;$$

CREATE TRIGGER insert_accept_payment AFTER INSERT
    ON AcceptPayment
    FOR EACH ROW
    BEGIN
        IF ((
            SELECT COUNT(*)
            FROM Signature
            WHERE payment_order = NEW.payment_order
            ) < (
            SELECT signature_number
            FROM PaymentOrder JOIN Account ON Account.ID = PaymentOrder.account
            WHERE PaymentOrder.ID = NEW.payment_order
            )) THEN 
                SIGNAL sqlstate '45001' set message_text = "Can not accept this PaymentOrder because signatures are not enough. ";
        
        ELSEIF (NOT EXISTS (
            SELECT *
            FROM AcceptAccess JOIN PaymentOrder ON AcceptAccess.account = PaymentOrder.account
            WHERE AcceptAccess.customer = NEW.customer AND PaymentOrder.ID = NEW.payment_order
        )) THEN 
            SIGNAL sqlstate '45001' set message_text = "This user has no AcceptAccess permission on payment of this account.";
        
        ELSEIF((
            SELECT DISTINCT(Account.amount) 
            FROM Account JOIN PaymentOrder ON Account.ID = PaymentOrder.account
            WHERE PaymentOrder.ID = NEW.payment_order 
        ) < (
            SELECT SUM(Transaction.amount)
            FROM PaymentOrder JOIN Transaction ON PaymentOrder.ID = Transaction.payment_order
            WHERE PaymentOrder.ID = NEW.payment_order
        )) THEN
            SIGNAL sqlstate '45001' set message_text = "Not enough money for paying all Transactions of this PaymentOrder.";

        ELSE 
            -- CHECKOUT
            INSERT INTO Bill(account, amount, note, bill_type) 
                SELECT Transaction.destination, Transaction.amount, PaymentOrder.note, "b1"
                FROM Transaction JOIN PaymentOrder ON Transaction.payment_order = PaymentOrder.ID
                WHERE PaymentOrder.ID = NEW.payment_order;
            -- DEPOSIT
            INSERT INTO Bill(account, amount, note, bill_type) 
                SELECT PaymentOrder.account, Transaction.amount, PaymentOrder.note, "b2"
                FROM Transaction JOIN PaymentOrder ON Transaction.payment_order = PaymentOrder.ID
                WHERE PaymentOrder.ID = NEW.payment_order;
        END IF;
    END;$$

    CREATE TRIGGER insert_bill AFTER INSERT
    ON Bill
    FOR EACH ROW
    BEGIN
        IF (NEW.bill_type = "b1") THEN 
            UPDATE Account
            SET amount = amount + NEW.amount 
            WHERE NEW.account = ID;
        ELSEIF (NEW.bill_type = "b2") THEN 
            UPDATE Account
            SET amount = amount - NEW.amount 
            WHERE NEW.account = ID;
        END IF;
    END;$$

DELIMITER ;