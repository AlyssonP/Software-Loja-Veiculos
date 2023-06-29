INSERT INTO tipo_veiculo (id, nome) values (1,'carro'), (2, 'moto');

INSERT INTO veiculo (id_tipo_veiculo, placa, marca, modelo, ano, cor, preco_venda, quilometragem, vendido) values
(1,'OAO1A58', 'VW', 'POLO', 2022,'Branco', 80400, 30, false),
(2,'AAA1S34','Honda', 'POP', 2023, 'Branca', 11000,0.9,false);

INSERT INTO carro (id_veiculo, quantidade_portas, tipo_combustivel, cambio) values
(1,5,'Flex','Automatico');

INSERT INTO moto (id_veiculo, cilindrada) values
(2,110);

INSERT INTO tipo_pagamento(id, nome) VALUES (1,'A vista'),(2,'Financiado');

INSERT INTO forma_pagamento (id_tipo_pagamento, nome) VALUES (1,'Pagamento Pix'),(2,'Pagamento Financiado');

INSERT INTO pagamento_avista (id_forma_pagamento, percentual_desconto) VALUES (1, 10);

INSERT INTO pagamento_financiado (id_forma_pagamento, taxa_juros, quantidade_parcelas) VALUES
(2, 2.4, 36);
