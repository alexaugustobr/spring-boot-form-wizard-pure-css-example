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

CREATE TABLE core.possession (
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT possession_pkey PRIMARY KEY (id)
);

CREATE TABLE core.users (
	id serial NOT NULL,
	active bool NOT NULL,
	age int4 NOT NULL,
	creation_date date NULL,
	email varchar(255) NOT NULL,
	last_login_date date NULL,
	"name" varchar(255) NULL,
	status int4 NULL,
	CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE core.users_possession_list (
	user_id int4 NOT NULL,
	possession_list_id int8 NOT NULL,
	CONSTRAINT uk_bpwe7cygco1srvh963nnh8cwm UNIQUE (possession_list_id),
	CONSTRAINT fkn8m8mn54e7sxb1c5hjw7b7o6x FOREIGN KEY (user_id) REFERENCES core.users(id),
	CONSTRAINT fktmvofjb4iurf3p4rrk7rtak38 FOREIGN KEY (possession_list_id) REFERENCES core.possession(id)
);
