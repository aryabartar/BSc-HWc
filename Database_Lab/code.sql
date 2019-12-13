CREATE FUNCTION reffunc(refcursor) RETURNS refcursor AS '
BEGIN
    OPEN $1 FOR SELECT * FROM "tb1";
    RETURN $1;
END;
' LANGUAGE plpgsql;

BEGIN;
SELECT reffunc('funccursor');

FETCH 1 IN funccursor;
MOVE FORWARD 1 FROM funccursor;
FETCH 2 IN funccursor;

COMMIT;