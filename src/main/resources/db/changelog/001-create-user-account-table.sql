CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE user_account
(
    id         UUID                        NOT NULL,
    username   VARCHAR(255),
    password   VARCHAR(255),
    role       VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT (CURRENT_TIMESTAMP AT TIME ZONE 'UTC'),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE user_account
    ADD CONSTRAINT uc_users_username UNIQUE (username);