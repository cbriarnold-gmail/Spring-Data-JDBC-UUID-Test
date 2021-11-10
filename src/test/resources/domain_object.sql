Drop table if exists domain_object cascade;

-- Table: domain_object

CREATE TABLE domain_object
(
    id uuid NOT NULL,
    value VARCHAR(100) not null,
    CONSTRAINT domain_object_pkey PRIMARY KEY (id)
);