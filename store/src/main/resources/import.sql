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
