CREATE DATABASE mkdata
  WITH ENCODING='UTF8'
       OWNER=postgres
       CONNECTION LIMIT=-1;
	   


CREATE SEQUENCE cliente_cli_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE cliente_cli_id_seq OWNER TO postgres;


CREATE TABLE cliente
(
  cli_id integer NOT NULL DEFAULT nextval('cliente_cli_id_seq'::regclass),
  cli_nome character varying(100) NOT NULL,
  cli_tipo character varying(10) NOT NULL,
  cli_cpf_cnpj character varying(14) NOT NULL,
  cli_rg_ie character varying(50),
  cli_data_cadastro timestamp without time zone,
  cli_ativo integer,
  CONSTRAINT clientes_pkey PRIMARY KEY (cli_id),
  CONSTRAINT clientes_cli_id_key UNIQUE (cli_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente OWNER TO postgres;

CREATE SEQUENCE contato_con_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE contato_con_id_seq OWNER TO postgres;

CREATE TABLE contato
(
  con_id integer NOT NULL DEFAULT nextval('contato_con_id_seq'::regclass),
  com_descricao character varying(100) NOT NULL,
  con_telefone character varying(13),
  cli_id integer,
  CONSTRAINT contato_pkey PRIMARY KEY (con_id),
  CONSTRAINT contato_cli_id_key UNIQUE (con_id),
  CONSTRAINT contato_fk_cli_id FOREIGN KEY (cli_id) REFERENCES cliente (cli_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contato OWNER TO postgres;
