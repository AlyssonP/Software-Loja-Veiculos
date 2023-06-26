INSERT INTO tipo_veiculo (id, nome) values (1,'carro'), (2, 'moto');

INSERT INTO veiculo (id_tipo_veiculo, placa, marca, modelo, ano, cor, preco_venda, quilometragem, vendido) values
(1,'OAO1A58', 'VW', 'POLO', 2022,'Branco', 80400, 30, false);

INSERT INTO carro (id_veiculo, quantidade_portas, tipo_combustivel, cambio) values
(1,5,'Flex','Automatico');