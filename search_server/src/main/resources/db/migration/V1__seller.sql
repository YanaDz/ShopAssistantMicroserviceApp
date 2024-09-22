CREATE TABLE IF NOT EXISTS seller (
                        id UUID NOT NULL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        search_url VARCHAR(255) NOT NULL,
                        title_class VARCHAR(255) NOT NULL,
                        title_product_element VARCHAR(255),
                        price_class VARCHAR(255),
                        price_element VARCHAR(255),
                        product_url VARCHAR(255),
                        location VARCHAR(255),
                        version BIGINT DEFAULT 0 NOT NULL,
                        created TIMESTAMP DEFAULT NOW() NOT NULL,
                        modified TIMESTAMP
);