CREATE TABLE product
(
    id          UUID                        NOT NULL,
    name        varchar                     NOT NULL,
    logo        BYTEA,
    category_id UUID                        NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT (CURRENT_TIMESTAMP AT TIME ZONE 'UTC'),
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT fk_product_on_category_id FOREIGN KEY (category_id) REFERENCES category (id);