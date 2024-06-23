BEGIN;

ALTER TABLE tb_product
    ADD COLUMN discount numeric(4, 2),
    ADD COLUMN weight numeric(10, 2),
    ADD COLUMN recommended_on_main_page boolean;

ALTER TABLE tb_category
    ADD COLUMN discount numeric(4, 2);

CREATE TABLE tb_product_size (
    id uuid default gen_random_uuid(),
    size varchar(5),
    product_id uuid,
    foreign key (product_id) references tb_product (id),
    primary key (id)
);

COMMIT;