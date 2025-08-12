SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET default_tablespace = '';

SET default_with_oids = false;

DROP TABLE IF EXISTS game;

CREATE TABLE game(
    id SERIAL PRIMARY KEY,
    map TEXT,
    player_x INTEGER,
    player_y INTEGER,
    health INTEGER,
    attack_power INTEGER,
    inventory TEXT
);