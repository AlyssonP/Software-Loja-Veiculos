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

CREATE TABLE tipo_veiculo (
	id integer PRIMARY KEY,
	nome varchar(100) NOT NULL
);

CREATE TABLE veiculo (
	id serial PRIMARY KEY,
	id_tipo_veiculo integer REFERENCES tipo_veiculo(id) NOT NULL,
	placa CHAR(7) UNIQUE NOT NULL,
	marca varchar(100) NOT NULL,
	modelo varchar(100) NOT NULL,
	ano integer NOT NULL,
	cor varchar(100) NOT NULL,
	preco_venda numeric(100,2) NOT NULL,
	quilometragem numeric(100,2) NOT NULL,
	vendido boolean NOT NULL
);

CREATE TABLE carro (
	id_veiculo integer REFERENCES veiculo(id) ON DELETE CASCADE PRIMARY KEY,
	quantidade_portas integer NOT NULL,
	tipo_combustivel varchar(100) NOT NULL,
	cambio varchar(100) NOT NULL
);

CREATE TABLE moto (
	id_veiculo integer REFERENCES veiculo(id) ON DELETE CASCADE PRIMARY KEY,
	cilindrada integer NOT NULL
);

CREATE TABLE tipo_pagamento(
	id INTEGER PRIMARY KEY,
	nome varchar(100) NOT NULL
);

CREATE TABLE forma_pagamento (
	id SERIAL PRIMARY KEY,
	id_tipo_pagamento INTEGER REFERENCES tipo_pagamento(id) NOT NULL,
	nome varchar(100) NOT NULL UNIQUE
);

CREATE TABLE pagamento_financiado (
	id_forma_pagamento integer REFERENCES forma_pagamento(id) ON DELETE CASCADE PRIMARY KEY,
	taxa_juros decimal(3,2) NOT NULL,
	quantidade_parcelas integer NOT NULL
);

CREATE TABLE pagamento_avista(
	id_forma_pagamento integer REFERENCES forma_pagamento(id) ON DELETE CASCADE PRIMARY KEY,
	percentual_desconto integer NOT NULL
);

CREATE TABLE venda (
	id serial PRIMARY KEY,
	id_veiculo integer REFERENCES veiculo(id) ON DELETE CASCADE NOT NULL UNIQUE,
	id_cliente integer REFERENCES cliente(id) ON DELETE CASCADE NOT NULL,
	id_forma_pagamento integer REFERENCES forma_pagamento(id) ON DELETE CASCADE NOT NULL,
	valor_final numeric(100,2) NOT NULL,
	data_venda date NOT NULL
);
