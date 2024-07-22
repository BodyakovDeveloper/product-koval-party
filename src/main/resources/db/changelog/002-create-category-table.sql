CREATE TABLE category
(
    id         UUID                        NOT NULL,
    name       VARCHAR(255),
    logo       BYTEA,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT (CURRENT_TIMESTAMP AT TIME ZONE 'UTC'),
    CONSTRAINT pk_category PRIMARY KEY (id)
);