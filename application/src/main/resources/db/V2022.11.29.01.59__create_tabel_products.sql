CREATE TABLE products
(
    id           SERIAL  NOT NULL,
    name         VARCHAR NOT NULL,
    organization_id INT     NOT NULL REFERENCES organizations (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL,
    amount       INT     NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id)
);