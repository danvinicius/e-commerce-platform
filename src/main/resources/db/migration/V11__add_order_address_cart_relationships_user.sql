BEGIN;

ALTER TABLE tb_order ADD COLUMN user_id UUID;
ALTER TABLE tb_address ADD COLUMN user_id UUID;
ALTER TABLE tb_cart ADD COLUMN user_id UUID;

ALTER TABLE tb_order ADD CONSTRAINT tb_order_user_id_fk FOREIGN KEY (user_id) REFERENCES tb_user(id);
ALTER TABLE tb_address ADD CONSTRAINT tb_address_user_id_fk FOREIGN KEY (user_id) REFERENCES tb_user(id);
ALTER TABLE tb_cart ADD CONSTRAINT tb_cart_user_id_fk FOREIGN KEY (user_id) REFERENCES tb_user(id);

COMMIT;