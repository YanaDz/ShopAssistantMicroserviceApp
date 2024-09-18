ALTER TABLE seller
    ADD COLUMN price_class VARCHAR(255);

UPDATE seller_element
SET element_type = 'div'
WHERE element_type = 0;

UPDATE seller_element
SET element_type = 'span'
WHERE element_type = 1;

UPDATE seller_element
SET element_type = 'a'
WHERE element_type = 2;

ALTER TABLE seller_element
ALTER COLUMN element_type TYPE VARCHAR(255) USING element_type::TEXT;

