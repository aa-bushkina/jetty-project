CREATE TABLE organizations
(
    id   SERIAL      NOT NULL,
    name VARCHAR     NOT NULL UNIQUE,
    CONSTRAINT organization_pk PRIMARY KEY (id)
);
