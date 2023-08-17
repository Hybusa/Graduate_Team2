-- liquibase formatted sql

-- changeset akuznetsov:1

CREATE TABLE images
(
    id         BIGSERIAL PRIMARY KEY,
    file_name  VARCHAR(32),
    media_type VARCHAR(16)
);

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    email      VARCHAR(64) UNIQUE NOT NULL,
    first_name VARCHAR(64),
    last_name  VARCHAR(64),
    password   VARCHAR(64),
    phone      VARCHAR(13),
    reg_date   DATE,
    image_id   BIGINT,
    role       VARCHAR(8),
    FOREIGN KEY (image_id) REFERENCES images (id)
        ON DELETE CASCADE
);

CREATE TABLE ads
(
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    image_id    BIGINT,
    price       INT,
    title       VARCHAR(32),
    description varchar(64),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (image_id) REFERENCES images (id)
        ON DELETE CASCADE
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
        ON DELETE CASCADE
);








