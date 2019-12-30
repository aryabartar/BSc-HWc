CREATE OR REPLACE PROCEDURE ctransfer(VARCHAR(128), VARCHAR(128) , NUMERIC(10,2) )
LANGUAGE plpgsql 
AS $$
BEGIN 

    UPDATE bank
    SET amount = amount - $3
    WHERE name = $1;

    UPDATE bank
    SET amount = amount + $3
    WHERE name = $23;

    -- COMMIT; optional
    -- ROLLBACK; rollbacks
    -- if error occurs -> No updates!
    
END;
$$;

CALL ctransfer('Arya', 'MahSa', 1000)

