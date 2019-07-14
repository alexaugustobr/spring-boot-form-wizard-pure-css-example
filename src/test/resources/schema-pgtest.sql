CREATE SCHEMA core;

CREATE SEQUENCE core.hibernate_sequence
NO MINVALUE
NO MAXVALUE;

CREATE TABLE core.cartorio (
	id int8 NOT NULL,
	email varchar(255) NULL,
	nome varchar(255) NULL,
	CONSTRAINT cartorio_pkey PRIMARY KEY (id)
);


CREATE TABLE core.documento (
	id int8 NOT NULL,
	nome varchar(255) NULL,
	CONSTRAINT documento_pkey PRIMARY KEY (id)
);

CREATE TABLE core.cartorio_documento (
	cartorio_list_id int8 NOT NULL,
	documento_list_id int8 NOT NULL,
	CONSTRAINT fka82bk2oqut1ft3g423yw8bue2 FOREIGN KEY (cartorio_list_id) REFERENCES core.cartorio(id),
	CONSTRAINT fke45dl0wml60v8afcscd69lh9 FOREIGN KEY (documento_list_id) REFERENCES core.documento(id)
);
