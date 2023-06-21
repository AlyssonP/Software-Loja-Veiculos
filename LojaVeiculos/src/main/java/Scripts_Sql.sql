CREATE TABLE  cliente (
	id serial PRIMARY KEY,
	cpf varchar(14) UNIQUE NOT NULL,
	nome varchar(200) NOT NULL,
	celular varchar(15) NOT NULL,
	email varchar(200) UNIQUE NOT NULL,
	data_nascimento date NOT NULL
);

CREATE  TABLE funcionario (
	id serial PRIMARY KEY,
	cpf varchar(14) UNIQUE NOT NULL,
	nome varchar(200) NOT NULL,
	celular varchar(15) NOT NULL,
	email varchar(200) UNIQUE NOT NULL,
	senha_acesso varchar(100) NOT NULL
);