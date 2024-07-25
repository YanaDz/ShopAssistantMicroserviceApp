CREATE TABLE IF NOT EXISTS translations_products
(
    id                     UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_id             UUID NOT NULL,
    product_translation_id UUID NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk_product_translation FOREIGN KEY (product_translation_id) REFERENCES product_translation (id)
);

