-- CREATE TABLE film(
--     title VARCHAR(128),
--     release_year INTEGER
-- )
-- ;

-- INSERT INTO film (title, release_year) VALUES ('f1', 1998);
-- INSERT INTO film(title, release_year)  VALUES ('f2', 1998);
-- INSERT INTO film(title, release_year)  VALUES ('f3', 1998);
-- INSERT INTO film (title, release_year) VALUES ('f4', 1998);
-- INSERT INTO film(title, release_year)  VALUES ('f4', 1999);

-- CREATE OR REPLACE FUNCTION get_film_titles(p_year INTEGER)
--    RETURNS text AS $$
-- DECLARE 
--     titles TEXT DEFAULT '';
--     rec_film   RECORD;
--     cur_films CURSOR
--        FOR 
--        SELECT title, release_year
--        FROM film
--        WHERE release_year = 1998;

-- BEGIN
--    OPEN cur_films;
   
--       FETCH cur_films INTO rec_film;
--       titles := titles || ',' || rec_film.title || ':' || rec_film.release_year;
--       MOVE FORWARD 2 FROM cur_films;

--         UPDATE film 
--         SET title = 'Arya'
--         WHERE CURRENT OF cur_films;


--       FETCH cur_films INTO rec_film;
--       titles := titles || ',' || rec_film.title || ':' || rec_film.release_year;


--         DELETE FROM film 
--         WHERE CURRENT OF cur_films;

--    CLOSE cur_films;
 
--    RETURN titles;
-- END; $$
-- LANGUAGE plpgsql;

-- SELECT get_film_titles(1998);

-- CREATE FUNCTION reffunc(refcursor) 
-- RETURNS refcursor AS 
-- $$
-- BEGIN
--     OPEN $1 FOR SELECT * FROM film;
--     RETURN $1;
-- END;
-- $$ 
-- LANGUAGE plpgsql;

-- BEGIN;
-- SELECT reffunc('funccursor');
-- FETCH 1 IN funccursor;
-- FETCH 1 IN funccursor;
-- FETCH 1 IN funccursor;
-- COMMIT;

-- BEGIN;

-- DECLARE 
-- cur_films  CURSOR FOR SELECT * FROM film;
-- -- OPEN cur_films;
-- FETCH 1 IN cur_films;


-- COMMIT;