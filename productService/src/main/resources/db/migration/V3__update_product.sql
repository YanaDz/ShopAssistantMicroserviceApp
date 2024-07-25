ALTER TABLE product
ALTER COLUMN product_type TYPE INT USING product_type::INT;