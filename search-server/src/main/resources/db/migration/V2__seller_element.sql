CREATE TABLE seller_element (
                                id UUID NOT NULL PRIMARY KEY,
                                element_type VARCHAR(255),
                                element_name VARCHAR(255) NOT NULL,
                                priority INT NOT NULL DEFAULT 0,
                                seller_element_field INT NOT NULL,
                                seller_id UUID NOT NULL,
                                version BIGINT DEFAULT 0 NOT NULL,
                                created TIMESTAMP DEFAULT NOW() NOT NULL,
                                modified TIMESTAMP,
                                FOREIGN KEY (seller_id) REFERENCES seller(id) ON DELETE CASCADE
);
CREATE INDEX idx_seller_element_seller_id ON seller_element(seller_id);

ALTER TABLE seller
DROP COLUMN title_product_element,
DROP COLUMN price_element;