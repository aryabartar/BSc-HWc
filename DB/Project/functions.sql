DROP FUNCTION get_hashed_password;
DROP FUNCTION get_signature_access_number;
DROP FUNCTION get_signature_number;
DROP FUNCTION get_total_payment_order_amount;


DELIMITER $$

CREATE FUNCTION get_hashed_password(
    essn VARCHAR(10), epassword VARCHAR(256)) RETURNS VARCHAR(256)
BEGIN
    DECLARE customer_id INT; 
    DECLARE hashed_password VARCHAR(256); 
    DECLARE ehashed_password VARCHAR(256); 

    SELECT C.customer_id INTO customer_id
    FROM Customer AS C
    WHERE ssn = essn; 

    SELECT C.password INTO hashed_password
    FROM Customer AS C
    WHERE ssn = essn; 

    SET ehashed_password = MD5(CONCAT(customer_id, epassword));

    IF (ehashed_password = hashed_password) THEN 
        RETURN 1; 
    ELSE 
        RETURN 0;
    END IF;

END;$$

CREATE FUNCTION get_signature_access_number(
    account_id INT) RETURNS INT
BEGIN
    DECLARE rsignature_access_number INT;
    
    SELECT count(*) INTO rsignature_access_number
    FROM SignatureAccess
    WHERE SignatureAccess.account = account_id;
    
    RETURN rsignature_access_number;
END;$$

CREATE FUNCTION get_signature_number(
    payment_order_id INT) RETURNS INT
BEGIN
    DECLARE rsignature_number INT;
    
    SELECT count(*) INTO rsignature_number
    FROM Signature 
    WHERE Signature.payment_order = payment_order_id;
    
    RETURN rsignature_number;
END;$$

CREATE FUNCTION get_total_payment_order_amount(
    payment_order_id INT) RETURNS INT
BEGIN
    DECLARE total_amount INT;
    
    SELECT SUM(Transaction.amount) INTO total_amount
    FROM PaymentOrder JOIN Transaction ON PaymentOrder.ID = Transaction.payment_order
    WHERE PaymentOrder.ID = payment_order_id;
    
    RETURN total_amount;
END;$$





DELIMITER ;
