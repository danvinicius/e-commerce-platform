ALTER TRIGGER update_updated_at_trigger ON tb_product
RENAME TO update_product_updated_at_trigger;

ALTER TABLE tb_order
ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- update order updated at trigger

CREATE TRIGGER update_order_updated_at_trigger
BEFORE UPDATE ON tb_order
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

ALTER TABLE tb_category
ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- update category updated at trigger

CREATE TRIGGER update_category_updated_at_trigger
BEFORE UPDATE ON tb_category
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

