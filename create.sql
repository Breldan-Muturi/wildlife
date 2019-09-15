CREATE DATABASE wildlife;
\c wildlife;

CREATE TABLE IF NOT EXISTS animals(
id SERIAL PRIMARY KEY,
animal_id INTEGER,
animal_name VARCHAR
);

CREATE TABLE IF NOT EXISTS endangeredAnimals(
id SERIAL PRIMARY KEY,
animal_id INTEGER,
animal_name VARCHAR,
animal_age VARCHAR,
animal_health VARCHAR
);

CREATE TABLE IF NOT EXISTS sighting(
id SERIAL PRIMARY KEY,
animal_id INTEGER,
animal_location VARCHAR,
ranger_name VARCHAR,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE DATABASE wildlife_test WITH TEMPLATE wildlife;

