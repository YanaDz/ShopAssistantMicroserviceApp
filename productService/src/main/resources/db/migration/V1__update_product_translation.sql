-- Ensure the extension for UUID generation is enabled
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER TABLE product_translation
    DROP COLUMN product_id,
    ADD COLUMN translation_type     INT  NOT NULL,
    ALTER COLUMN location TYPE INT USING location::INT,
    add COLUMN basic_translation_id uuid null;



