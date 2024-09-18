INSERT INTO seller
(id, name, search_url, title_class, product_url, location, version, created, modified)
VALUES
(gen_random_uuid(), 'Rossman', 'https://www.rossmann.pl/szukaj?Search=', 'ProductTile-module_productTile--L', '', '1', 0, NOW(), null),
(gen_random_uuid(), 'MakeUp', 'https://makeup.pl/search/?q=', 'simple-slider-list__link', '', '1', 0, NOW(), null);

INSERT INTO seller_element
(id, element_type, element_name, priority, seller_element_field, seller_id, version, created, modified)
VALUES
(gen_random_uuid(), 0, 'DescriptionSection-module_descriptionSection--YbLmv', 0, 0, (select id from seller where name like 'Rossman'), 0, NOW(), null),
(gen_random_uuid(), 1, 'span.PriceSection-module_price--I18Do', 0, 1, (select id from seller where name like 'Rossman'), 0, NOW(), null),
(gen_random_uuid(), 2, 'a.simple-slider-list__name', 1, 0, (select id from seller where name like 'MakeUp'), 0, NOW(), null),
(gen_random_uuid(), 0, 'simple-slider-list__description', 2, 0, (select id from seller where name like 'MakeUp'), 0, NOW(), null),
(gen_random_uuid(), 1, '.simple-slider-list__price .price_item', 1, 1, (select id from seller where name like 'MakeUp'), 0, NOW(), null);
