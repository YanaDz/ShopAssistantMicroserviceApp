CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE firm
(
    id       UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name     VARCHAR(255) NOT NULL,
    version  BIGINT           DEFAULT 0,
    created  TIMESTAMP        DEFAULT NOW(),
    modified TIMESTAMP
);

CREATE TABLE seller
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name       VARCHAR(255) NOT NULL,
    search_url VARCHAR(255),
    version    BIGINT           DEFAULT 0,
    created    TIMESTAMP        DEFAULT NOW(),
    modified   TIMESTAMP
);

CREATE TABLE product
(
    id              UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name            VARCHAR(255) NOT NULL,
    product_version VARCHAR(255),
    product_type    VARCHAR(255),
    firm_id         UUID,
    version         BIGINT           DEFAULT 0,
    created         TIMESTAMP        DEFAULT NOW(),
    modified        TIMESTAMP,
    CONSTRAINT fk_firm FOREIGN KEY (firm_id) REFERENCES firm (id)
);

CREATE TABLE product_translation
(
    id                   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_id           UUID,
    location             VARCHAR(255),
    location_translation VARCHAR(255),
    version              BIGINT           DEFAULT 0,
    created              TIMESTAMP        DEFAULT NOW(),
    modified             TIMESTAMP,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE purchase
(
    id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_id  UUID,
    seller_id   UUID,
    price       DOUBLE PRECISION,
    product_url VARCHAR(255),
    version     BIGINT           DEFAULT 0,
    created     TIMESTAMP        DEFAULT NOW(),
    modified    TIMESTAMP,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk_seller FOREIGN KEY (seller_id) REFERENCES seller (id)
);

