ALTER TABLE seller
DROP COLUMN price_class;

UPDATE seller_element
SET element_type = '0'
WHERE element_type like 'div';

UPDATE seller_element
SET element_type = '1'
WHERE element_type like 'span';

UPDATE seller_element
SET element_type = '2'
WHERE element_type like 'a';

ALTER TABLE seller_element
ALTER COLUMN element_type TYPE INT USING element_type::INTEGER;

