CREATE TABLE organizations
(
    id   SERIAL      NOT NULL,
    name VARCHAR     NOT NULL UNIQUE,
    inn  VARCHAR(10) NOT NULL UNIQUE,
    CONSTRAINT organization_pk PRIMARY KEY (id),
    CONSTRAINT check_inn CHECK (inn ~ '\d{10}' )
);
