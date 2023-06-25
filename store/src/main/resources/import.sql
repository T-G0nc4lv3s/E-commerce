INSERT INTO tb_category (name) VALUES ('Roupas');
INSERT INTO tb_category (name) VALUES ('Calçados');
INSERT INTO tb_category (name) VALUES ('Livros');
INSERT INTO tb_category (name) VALUES ('Eletrônicos');
INSERT INTO tb_category (name) VALUES ('Celulares');
INSERT INTO tb_category (name) VALUES ('Computadores');
INSERT INTO tb_category (name) VALUES ('Móveis');
INSERT INTO tb_category (name) VALUES ('Veículos');


INSERT INTO tb_product (name, price) VALUES ('IPhone X PRO', 8000.0);
INSERT INTO tb_product (name, price) VALUES ('ONIX LTZ', 90000.0);
INSERT INTO tb_product (name, price) VALUES ('AMD RYZEM 5', 6000.0);

INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 5);
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 8);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 6);

INSERT INTO tb_state (name, acronym) VALUES ('Acre', 'AC');
INSERT INTO tb_state (name, acronym) VALUES ('Alagoas', 'AL');
INSERT INTO tb_state (name, acronym) VALUES ('Amapá', 'AP');
INSERT INTO tb_state (name, acronym) VALUES ('Amazonas', 'AM');
INSERT INTO tb_state (name, acronym) VALUES ('Bahia', 'BA');
INSERT INTO tb_state (name, acronym) VALUES ('Ceará', 'CE');
INSERT INTO tb_state (name, acronym) VALUES ('Espírito Santo', 'ES');
INSERT INTO tb_state (name, acronym) VALUES ('Goiás', 'GO');
INSERT INTO tb_state (name, acronym) VALUES ('Maranhão', 'MA');
INSERT INTO tb_state (name, acronym) VALUES ('Mato Grosso', 'MT');
INSERT INTO tb_state (name, acronym) VALUES ('Mato Grosso do Sul', 'MS');
INSERT INTO tb_state (name, acronym) VALUES ('Minas Gerais', 'MG');
INSERT INTO tb_state (name, acronym) VALUES ('Pará', 'PA');
INSERT INTO tb_state (name, acronym) VALUES ('Paraíba', 'PB');
INSERT INTO tb_state (name, acronym) VALUES ('Paraná', 'PR');
INSERT INTO tb_state (name, acronym) VALUES ('Pernambuco', 'PE');
INSERT INTO tb_state (name, acronym) VALUES ('Piauí', 'PI');
INSERT INTO tb_state (name, acronym) VALUES ('Rio de Janeiro', 'RJ');
INSERT INTO tb_state (name, acronym) VALUES ('Rio Grande do Norte', 'RN');
INSERT INTO tb_state (name, acronym) VALUES ('Rio Grande do Sul', 'RS');
INSERT INTO tb_state (name, acronym) VALUES ('Rondônia', 'RO');
INSERT INTO tb_state (name, acronym) VALUES ('Roraima','RR');
INSERT INTO tb_state (name, acronym) VALUES ('Santa Catarina', 'SC');
INSERT INTO tb_state (name, acronym) VALUES ('São Paulo', 'SP');
INSERT INTO tb_state (name, acronym) VALUES ('Sergipe', 'SE');
INSERT INTO tb_state (name, acronym) VALUES ('Tocantins', 'TO');
INSERT INTO tb_state (name, acronym) VALUES ('Distrito Federal', 'DF');

INSERT INTO tb_city(name, state_id) VALUES ('São Paulo', 24);
INSERT INTO tb_city(name, state_id) VALUES ('Rio de Janeiro', 18);
INSERT INTO tb_city(name, state_id) VALUES ('São João de Meriti', 18);

INSERT INTO tb_client (name, email, cpf_or_cnpj, type) VALUES ('Alex Green', 'alex@gmail.com', '756.274.344-49', 'PHYSICAL_PERSON');
INSERT INTO tb_client (name, email, cpf_or_cnpj, type) VALUES ('Martha Black', 'martha@gmail.com', '127.457.555-95', 'PHYSICAL_PERSON');
INSERT INTO tb_client (name, email, cpf_or_cnpj, type) VALUES ('CarTop', 'cartop@gmail.com', '52.751.884/0001-10', 'LEGAL_PERSON');

INSERT INTO tb_phones (client_id, phones) VALUES (1, '(66) 99711-4026');
INSERT INTO tb_phones (client_id, phones) VALUES (2, '(21) 99771-4046');
INSERT INTO tb_phones (client_id, phones) VALUES (3, '(87) 98874-5913');

INSERT INTO tb_address(street, number, district, complement, zip_code, client_id, city_id) VALUES ('Beco do crime', 's/nº', 'Gothan', null, '11111111', 1, 1);
INSERT INTO tb_address(street, number, district, complement, zip_code, client_id, city_id) VALUES ('Praça da Sé', 'S/Nº', 'Sé', null, '01001000', 1, 2);
INSERT INTO tb_address(street, number, district, complement, zip_code, client_id, city_id) VALUES ('Rua Dona Júlia Cheven', 'S/Nº', 'São Mateus', null, '25520200', 2, 3);
INSERT INTO tb_address(street, number, district, complement, zip_code, client_id, city_id) VALUES ('Santa Edwiges', 'S/Nº','Gotham', null, 'xxxxxxxx', 3, 3);

