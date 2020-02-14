CREATE DATABASE godel_test;
CREATE SCHEMA godel;
CREATE TABLE godel.director (
    id integer NOT NULL primary key,
    first_name character varying,
    last_name character varying,
    birth_date timestamp without time zone
);
CREATE TABLE godel.film (
    id integer NOT NULL primary key,
    director_id integer,
    name character varying,
    release_date timestamp without time zone,
    genre character varying
);
ALTER TABLE ONLY godel.film
ADD CONSTRAINT director_id_foreign FOREIGN KEY (director_id) REFERENCES godel.director(id);
ALTER TABLE ONLY godel.film DROP CONSTRAINT director_id_foreign;

INSERT INTO godel.director(id, first_name, last_name, birth_date) VALUES(1, `Raman`, `Zamoiski`, `1998-04-08 00:00:01`);
INSERT INTO godel.director(id, first_name, last_name, birth_date) VALUES(2, `Steven`, `Spilberg`, `1946-11-18 00:53:55`);
INSERT INTO godel.director(id, first_name, last_name, birth_date) VALUES(3, `David`, `Fincher`, `1962-07-28 00:00:01`);
INSERT INTO godel.director(id, first_name, last_name, birth_date) VALUES(4, `James`, `Cameron`, `1954-07-16 00:00:01`);

INSERT INTO godel.film(id, director_id, name, release_date, genre) VALUES(1, 2, `Amblin`, `1968-01-01 00:00:01`, `Drama`);
INSERT INTO godel.film(id, director_id, name, release_date, genre) VALUES(1, 2, `Duel`, `1971-01-01 00:00:01`, `Thriller`);
INSERT INTO godel.film(id, director_id, name, release_date, genre) VALUES(1, 2, `Always`, `1989-01-01 00:00:01`, `Thriller`);
INSERT INTO godel.film(id, director_id, name, release_date, genre) VALUES(1, 2, `Indiana Jones and the Temple of Doom`, `1989-01-01 00:00:01`, `Adventure`);