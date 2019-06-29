DROP TRIGGER update_customer;
DROP TRIGGER delete_customer;
DROP TRIGGER update_phone_number;
DROP TRIGGER delete_phone_number;
DROP TRIGGER update_address;
DROP TRIGGER delete_address;
DROP TRIGGER update_account;
DROP TRIGGER delete_account;
DROP TRIGGER insert_payment_order;
DROP TRIGGER update_payment_order;
DROP TRIGGER delete_payment_order;
DROP TRIGGER insert_transaction;
DROP TRIGGER update_transaction;
DROP TRIGGER delete_transaction;
DROP TRIGGER insert_bill;
DROP TRIGGER update_bill;
DROP TRIGGER delete_bill;
DROP TRIGGER update_account_owner;
DROP TRIGGER delete_account_owner;
DROP TRIGGER update_signature_access;
DROP TRIGGER delete_signature_access;
DROP TRIGGER update_accept_access;
DROP TRIGGER delete_accept_access;
DROP TRIGGER update_view_account_access;
DROP TRIGGER delete_view_account_access;
DROP TRIGGER update_settings;
DROP TRIGGER delete_settings;
DROP TRIGGER update_signature;
DROP TRIGGER delete_signature;
DROP TRIGGER insert_accept_payment;
DROP TRIGGER update_accept_payment;
DROP TRIGGER delete_accept_payment;
DROP TRIGGER insert_signature;
DROP TRIGGER insert_customer;


DELIMITER $$


-- Customer 
CREATE TRIGGER insert_customer BEFORE INSERT 
    ON Customer
    FOR EACH ROW
    BEGIN
        SET NEW.password_update_time = CURRENT_TIME;
        SET NEW.password = SHA2(CONCAT(NEW.ssn, NEW.password_update_time, NEW.password), 256);
    END;$$

CREATE TRIGGER update_customer BEFORE UPDATE 
    ON Customer
    FOR EACH ROW
    BEGIN
        IF (NEW.password <> OLD.password) THEN
            SET NEW.password_update_time = CURRENT_TIME;
            SET NEW.password = SHA2(CONCAT(NEW.ssn, NEW.password_update_time, NEW.password), 256);
        END IF;

        INSERT INTO CustomerHistory(ssn, firstname, lastname, customer_id, create_time) 
            VALUES (OLD.ssn, OLD.firstname, OLD.lastname, OLD.customer_id, OLD.create_time);
    END;$$

CREATE TRIGGER delete_customer AFTER DELETE 
    ON Customer
    FOR EACH ROW
    BEGIN
        INSERT INTO CustomerHistory(ssn, firstname, lastname, customer_id, create_time) 
            VALUES (OLD.ssn, OLD.firstname, OLD.lastname, OLD.customer_id, OLD.create_time);
    END;$$



-- PhoneNumber
CREATE TRIGGER update_phone_number AFTER UPDATE 
    ON PhoneNumber
    FOR EACH ROW
    BEGIN
        INSERT INTO PhoneNumberHistory(ssn, number, create_time) 
            VALUES (OLD.ssn, OLD.number, OLD.create_time);
    END;$$

CREATE TRIGGER delete_phone_number AFTER DELETE 
    ON PhoneNumber
    FOR EACH ROW
    BEGIN
        INSERT INTO PhoneNumberHistory(ssn, number, create_time) 
            VALUES (OLD.ssn, OLD.number, OLD.create_time);
    END;$$



-- Address 
CREATE TRIGGER update_address AFTER UPDATE 
    ON Address
    FOR EACH ROW
    BEGIN
        INSERT INTO AddressHistory(ssn, address, create_time) 
            VALUES (OLD.ssn, OLD.address, OLD.create_time);
    END;$$

CREATE TRIGGER delete_address AFTER DELETE 
    ON Address
    FOR EACH ROW
    BEGIN
        INSERT INTO AddressHistory(ssn, address, create_time) 
            VALUES (OLD.ssn, OLD.address, OLD.create_time);
    END;$$



-- Account 
CREATE TRIGGER update_account AFTER UPDATE 
    ON Account
    FOR EACH ROW
    BEGIN
        INSERT INTO AccountHistory(ID, amount, account_type, signature_number, create_time) 
            VALUES (OLD.ID, OLD.amount, OLD.account_type, OLD.signature_number, OLD.create_time);
    END;$$

CREATE TRIGGER delete_account AFTER DELETE 
    ON Account
    FOR EACH ROW
    BEGIN
        INSERT INTO AccountHistory(ID, amount, account_type, signature_number, create_time) 
            VALUES (OLD.ID, OLD.amount, OLD.account_type, OLD.signature_number, OLD.create_time);
    END;$$



-- PaymentOrder 
CREATE TRIGGER insert_payment_order AFTER INSERT 
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
        ELSE 
            INSERT INTO PaymentOrderHistory(ID, account, creator, note, create_time) 
                VALUES (OLD.ID, OLD.account, OLD.creator, OLD.note, OLD.create_time);
        END IF;
    END;$$

CREATE TRIGGER delete_payment_order AFTER DELETE 
    ON PaymentOrder
    FOR EACH ROW
    BEGIN
        INSERT INTO PaymentOrderHistory(ID, account, creator, note, create_time) 
            VALUES (OLD.ID, OLD.account, OLD.creator, OLD.note, OLD.create_time);
    END;$$



-- Transaction 
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
        ELSE
            INSERT INTO TransactionHistory(payment_order, destination, amount, create_time) 
                VALUES (OLD.payment_order, OLD.destination, OLD.amount, OLD.create_time);
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
        ELSE 
            INSERT INTO TransactionHistory(payment_order, destination, amount, create_time) 
                VALUES (OLD.payment_order, OLD.destination, OLD.amount, OLD.create_time);
        END IF;
    END;$$



-- Bill 
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

CREATE TRIGGER update_bill AFTER UPDATE 
    ON Bill
    FOR EACH ROW
    BEGIN
        INSERT INTO BillHistory(ID, account, amount, bill_type, note, create_time) 
            VALUES (OLD.ID, OLD.account, OLD.amount, OLD.bill_type, OLD.note, OLD.create_time);
    END;$$
    
CREATE TRIGGER delete_bill AFTER DELETE 
    ON Bill
    FOR EACH ROW
    BEGIN
        INSERT INTO BillHistory(ID, account, amount, bill_type, note, create_time) 
            VALUES (OLD.ID, OLD.account, OLD.amount, OLD.bill_type, OLD.note, OLD.create_time);
    END;$$



-- AccountOwner
CREATE TRIGGER update_account_owner AFTER UPDATE
ON AccountOwner
FOR EACH ROW
BEGIN
    INSERT INTO AccountOwnerHistory(customer, account, create_time) VALUES (OLD.customer, OLD.account, OLD.create_time); 
END;$$

CREATE TRIGGER delete_account_owner AFTER DELETE
ON AccountOwner
FOR EACH ROW
BEGIN
    INSERT INTO AccountOwnerHistory(customer, account, create_time) VALUES (OLD.customer, OLD.account, OLD.create_time); 
END;$$



-- SignatureAccess 
CREATE TRIGGER update_signature_access AFTER UPDATE
ON SignatureAccess
FOR EACH ROW
BEGIN
    INSERT INTO SignatureAccessHistory(customer, account, create_time) VALUES (OLD.customer, OLD.account, OLD.create_time); 
END;$$

CREATE TRIGGER delete_signature_access AFTER DELETE
ON SignatureAccess
FOR EACH ROW
BEGIN
    INSERT INTO SignatureAccessHistory(customer, account, create_time) VALUES (OLD.customer, OLD.account, OLD.create_time); 
END;$$



-- AcceptAccess
CREATE TRIGGER update_accept_access AFTER UPDATE
ON AcceptAccess
FOR EACH ROW
BEGIN
    INSERT INTO AcceptAccessHistory(customer, account, create_time) VALUES (OLD.customer, OLD.account, OLD.create_time); 
END;$$

CREATE TRIGGER delete_accept_access AFTER DELETE
ON AcceptAccess
FOR EACH ROW
BEGIN
    INSERT INTO AcceptAccessHistory(customer, account, create_time) VALUES (OLD.customer, OLD.account, OLD.create_time); 
END;$$



-- ViewAccountAccess
CREATE TRIGGER update_view_account_access AFTER UPDATE
ON ViewAccountAccess
FOR EACH ROW
BEGIN
    INSERT INTO ViewAccountAccessHistory(customer, account, create_time) VALUES (OLD.customer, OLD.account, OLD.create_time); 
END;$$

CREATE TRIGGER delete_view_account_access AFTER DELETE
ON ViewAccountAccess
FOR EACH ROW
BEGIN
    INSERT INTO ViewAccountAccessHistory(customer, account, create_time) VALUES (OLD.customer, OLD.account, OLD.create_time); 
END;$$



-- Settings
CREATE TRIGGER update_settings AFTER UPDATE
ON Settings
FOR EACH ROW
BEGIN
    INSERT INTO SettingsHistory(customer, account, account_name, color, create_time) VALUES (OLD.customer, OLD.account, OLD.account_name, OLD.color, OLD.create_time); 
END;$$

CREATE TRIGGER delete_settings AFTER DELETE
ON Settings
FOR EACH ROW
BEGIN
    INSERT INTO SettingsHistory(customer, account, account_name, color, create_time) VALUES (OLD.customer, OLD.account, OLD.account_name, OLD.color, OLD.create_time); 
END;$$



-- Signature
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

CREATE TRIGGER update_signature AFTER UPDATE
ON Signature
FOR EACH ROW
BEGIN
    INSERT INTO SignatureHistory(customer, payment_order, create_time) VALUES (OLD.customer, OLD.payment_order, OLD.create_time); 
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


-- AcceptPayment
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


CREATE TRIGGER update_accept_payment AFTER UPDATE
ON AcceptPayment
FOR EACH ROW
BEGIN
    INSERT INTO AcceptPaymentHistory(customer, payment_order, create_time) VALUES (OLD.customer, OLD.payment_order, OLD.create_time); 
END;$$

CREATE TRIGGER delete_accept_payment AFTER DELETE
ON AcceptPayment
FOR EACH ROW
BEGIN
    INSERT INTO AcceptPaymentHistory(customer, payment_order, create_time) VALUES (OLD.customer, OLD.payment_order, OLD.create_time); 
END;$$

DELIMITER ;