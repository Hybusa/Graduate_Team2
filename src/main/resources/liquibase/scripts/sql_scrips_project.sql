-- liquibase formatted sql

-- changeset akuznetsov:1

CREATE TABLE users (
                      email VARCHAR(255),
                      firstName VARCHAR(255),
                      id SERIAL,
                      lastName VARCHAR(255),
                      phone VARCHAR(255),
                      regDate VARCHAR(255),
                      city VARCHAR(255),
                      image VARCHAR(255)
);

CREATE TABLE comments (
                      postId SERIAL,
                      content VARCHAR(255),
                      authorId INTEGER,
                      FOREIGN KEY (authorId) REFERENCES users(id)
);
