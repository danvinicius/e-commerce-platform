DROP TABLE tb_product_size;

ALTER TABLE tb_product
    DROP quantity;

CREATE TABLE tb_product_stock (
    product_id uuid NOT NULL,
    size character varying(10) NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (product_id, size),
    FOREIGN KEY (product_id) REFERENCES tb_product (id)
);
