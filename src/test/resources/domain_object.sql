Drop table if exists domain_object cascade;
Drop table if exists domain_object_long_id cascade;

-- Table: domain_object

CREATE TABLE domain_object
(
    id uuid NOT NULL,
    value VARCHAR(100) not null,
    CONSTRAINT domain_object_pkey PRIMARY KEY (id)
);


CREATE TABLE domain_object_long_id
(
    id bigint NOT NULL,
    value VARCHAR(100) not null,
    CONSTRAINT domain_object_long_pkey PRIMARY KEY (id)
);