CREATE TABLE products
(
    id           SERIAL  NOT NULL,
    name         VARCHAR NOT NULL,
    organization VARCHAR NOT NULL UNIQUE,
    amount       INTEGER NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id)
);
