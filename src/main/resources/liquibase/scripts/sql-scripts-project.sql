-- liquibase formatted sql

-- changeset akuznetsov:1

CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    email     VARCHAR(255),
    firstName VARCHAR(255),
    lastName  VARCHAR(255),
    phone     VARCHAR(255),
    regDate   DATE,
    city      VARCHAR(255),
    image     VARCHAR(255),
    role      VARCHAR(255)
);

CREATE TABLE comments
(
    pk        SERIAL PRIMARY KEY,
    user_id   INT NOT NULL,
    createdAt TIMESTAMP,
    text      TEXT,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE ads
(
    pk      SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    image   VARCHAR(255),
    price   INT,
    title   VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (id)
);


