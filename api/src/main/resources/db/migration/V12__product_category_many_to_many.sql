ALTER TABLE tb_product DROP COLUMN category_id;

CREATE TABLE tb_product_category (
    product_id uuid,
    category_id uuid,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES tb_product (id),
    FOREIGN KEY (category_id) REFERENCES tb_category (id)
);