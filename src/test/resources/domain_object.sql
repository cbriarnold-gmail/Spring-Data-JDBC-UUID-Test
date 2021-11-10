Drop table if exists domain_object cascade;
Drop table if exists domain_object_long_id cascade;
Drop table if exists auto_domain_object_long_id cascade;

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

CREATE SEQUENCE IF NOT EXISTS auto_domain_object_long_id_seq;

CREATE TABLE auto_domain_object_long_id
(
    id bigint NOT NULL DEFAULT nextval('auto_domain_object_long_id_seq'::regclass),
    value VARCHAR(100) not null,
    CONSTRAINT auto_domain_object_long_pkey PRIMARY KEY (id)
);