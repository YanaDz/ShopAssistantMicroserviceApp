INSERT INTO seller (UUID, name, search_url, title_class, title_product_element, price_class, price_element, product_url, location, version, created)
VALUES (gen_random_uuid(), 'Rossman', 'https://www.rossmann.pl/szukaj?Search=', 'ProductTile-module_productTile--L', 'DescriptionSection-module_descriptionSection--YbLmv',
        'span.PriceSection-module_price--I18Do', '', '', 'PL', 0, NOW());
