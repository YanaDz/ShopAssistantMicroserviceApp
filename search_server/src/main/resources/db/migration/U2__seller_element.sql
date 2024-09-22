DROP TABLE IF EXISTS seller_element;

ALTER TABLE seller
    ADD COLUMN title_product_element VARCHAR(255),
ADD COLUMN price_element VARCHAR(255);