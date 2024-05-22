BEGIN;

ALTER TABLE tb_order_item ADD COLUMN id UUID DEFAULT gen_random_uuid();

UPDATE tb_order_item SET id = gen_random_uuid() WHERE id IS NULL;

ALTER TABLE tb_order_item DROP CONSTRAINT tb_order_item_pkey;

ALTER TABLE tb_order_item ADD PRIMARY KEY (id);

COMMIT;