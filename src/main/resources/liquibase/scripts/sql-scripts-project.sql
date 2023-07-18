-- liquibase formatted sql

-- changeset akuznetsov:1

CREATE TABLE images
(
    id         BIGSERIAL PRIMARY KEY,
    file_name  VARCHAR(255),
    media_type VARCHAR(255)
);

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    email      VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    password   VARCHAR(255),
    phone      VARCHAR(255),
    reg_date   DATE,
    image_id   BIGINT,
    role       VARCHAR(255),
    FOREIGN KEY (image_id) REFERENCES images (id)
);

CREATE TABLE ads
(
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    image_id    BIGINT,
    price       INT,
    title       VARCHAR(255),
    description varchar(255),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (image_id) REFERENCES images (id)
);


CREATE TABLE comments
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    created_at BIGINT,
    text       TEXT,
    ad_id      BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (ad_id) REFERENCES ads (id)
);








