CREATE TYPE ORDER_STATUS AS ENUM ('waiting_payment', 'paid', 'shipped', 'delivered', 'canceled');

ALTER TABLE tb_order ADD status ORDER_STATUS;