INSERT INTO firm (id, name, version, created)
VALUES (gen_random_uuid(), 'Apple', 0, NOW()),
       (gen_random_uuid(), 'Samsung', 0, NOW()),
       (gen_random_uuid(), 'Lenovo', 0, NOW()),
       (gen_random_uuid(), 'Dyson', 0, NOW()),
       (gen_random_uuid(), 'iRobot', 0, NOW()),
       (gen_random_uuid(), 'Vitamix', 0, NOW()),
       (gen_random_uuid(), 'Dell', 0, NOW()),
       (gen_random_uuid(), 'Microsoft', 0, NOW()),
       (gen_random_uuid(), 'OnePlus', 0, NOW()),
       (gen_random_uuid(), 'Google', 0, NOW()),
       (gen_random_uuid(), 'Sony', 0, NOW()),
       (gen_random_uuid(), 'KitchenAid', 0, NOW()),
       (gen_random_uuid(), 'Ninja', 0, NOW()),
       (gen_random_uuid(), 'HP', 0, NOW()),
       (gen_random_uuid(), 'LG', 0, NOW()),
       (gen_random_uuid(), 'Microsoft', 0, NOW());


INSERT INTO product (id, name, firm_id, product_version, product_type, version, created)
VALUES
    (gen_random_uuid(), 'iPhone 13', (select id from firm where name like 'Apple' LIMIT 1), '13', 2, 0, NOW()),
    (gen_random_uuid(), 'Galaxy S21', (select id from firm where name like 'Samsung' LIMIT 1), 'S21', 2, 0, NOW()),
    (gen_random_uuid(), 'MacBook Air', (select id from firm where name like 'Apple' LIMIT 1), 'M1', 3, 0, NOW()),
    (gen_random_uuid(), 'ThinkPad X1 Carbon', (select id from firm where name like 'Lenovo' LIMIT 1), 'Gen 9', 3, 0, NOW()),
    (gen_random_uuid(), 'Dyson V11', (select id from firm where name like 'Dyson' LIMIT 1), 'V11', 0, 0, NOW()),
    (gen_random_uuid(), 'Roomba i7', (select id from firm where name like 'iRobot' LIMIT 1), 'i7', 0, 0, NOW()),
    (gen_random_uuid(), 'Vitamix 5200', (select id from firm where name like 'Vitamix' LIMIT 1), '5200', 1, 0, NOW()),
    (gen_random_uuid(), 'Dell XPS 13', (select id from firm where name like 'Dell' LIMIT 1), '9310', 3, 0, NOW()),
    (gen_random_uuid(), 'Surface Pro 7', (select id from firm where name like 'Microsoft' LIMIT 1), 'Pro 7', 3, 0, NOW()),
    (gen_random_uuid(), 'OnePlus 9', (select id from firm where name like 'OnePlus' LIMIT 1), '9', 2, 0, NOW()),
    (gen_random_uuid(), 'Pixel 6', (select id from firm where name like 'Google' LIMIT 1), '6', 2, 0, NOW()),
    (gen_random_uuid(), 'PS5', (select id from firm where name like 'Sony' LIMIT 1), 'PlayStation 5', 0, 0, NOW()),
    (gen_random_uuid(), 'AirPods Pro', (select id from firm where name like 'Apple' LIMIT 1), 'Pro', 0, 0, NOW()),
    (gen_random_uuid(), 'KitchenAid Mixer', (select id from firm where name like 'KitchenAid' LIMIT 1), 'Artisan', 1, 0, NOW()),
    (gen_random_uuid(), 'Ninja Blender', (select id from firm where name like 'Ninja' LIMIT 1), 'BL770', 1, 0, NOW()),
    (gen_random_uuid(), 'HP Spectre x360', (select id from firm where name like 'HP' LIMIT 1), 'x360', 3, 0, NOW()),
    (gen_random_uuid(), 'Galaxy Tab S7', (select id from firm where name like 'Samsung' LIMIT 1), 'S7', 3, 0, NOW()),
    (gen_random_uuid(), 'iPad Pro', (select id from firm where name like 'Apple' LIMIT 1), 'M1', 3, 0, NOW()),
    (gen_random_uuid(), 'Sony Bravia X90J', (select id from firm where name like 'Sony' LIMIT 1), 'X90J', 0, 0, NOW()),
    (gen_random_uuid(), 'LG OLED CX', (select id from firm where name like 'LG' LIMIT 1), 'CX', 0, 0, NOW()),
    (gen_random_uuid(), 'Nest Thermostat', (select id from firm where name like 'Google' LIMIT 1), 'Thermostat', 0, 0, NOW()),
    (gen_random_uuid(), 'Sony WH-1000XM4', (select id from firm where name like 'Sony' LIMIT 1), 'XM4', 0, 0, NOW()),
    (gen_random_uuid(), 'Google Nest Hub', (select id from firm where name like 'Google' LIMIT 1), 'Hub', 0, 0, NOW()),
    (gen_random_uuid(), 'KitchenAid Toaster', (select id from firm where name like 'KitchenAid' LIMIT 1), 'KMT4116CU', 1, 0, NOW()),
    (gen_random_uuid(), 'Apple Watch Series 6', (select id from firm where name like 'Apple' LIMIT 1), 'Series 6', 0, 0, NOW()),
    (gen_random_uuid(), 'Samsung Galaxy Watch 4', (select id from firm where name like 'Samsung' LIMIT 1), 'Watch 4', 0, 0, NOW());

INSERT INTO seller (id, name, search_url, version, created)
VALUES (gen_random_uuid(), 'Euro-net', 'https://www.euro.com.pl/search.bhtml?keyword=', 1, NOW()),
       (gen_random_uuid(), 'MediaMarkt', 'https://mediamarkt.pl/pl/search.html?query=', 1, NOW());


INSERT INTO purchase (id, product_id, seller_id, price, product_url, version, created)
VALUES (gen_random_uuid(), (select id from product where name like 'Dyson V11'), (select id from seller where name like 'Euro-net'), 2099., 'https://www.euro.com.pl/odkurzacze-pionowe/dyson-v11.bhtml', 0, NOW()),
       (gen_random_uuid(), (select id from product where name like 'Dyson V11'), (select id from seller where name like 'MediaMarkt'), 2347., 'https://mediamarkt.pl/pl/product/_odkurzacz-bezprzewodowy-dyson-v11-1477006.html', 0, NOW()),
       (gen_random_uuid(), (select id from product where name like 'iPhone 13'), (select id from seller where name like 'Euro-net'), 4599. , 'https://www.euro.com.pl/telefony-komorkowe/apple-iphone-13-512gb-niebieski.bhtml', 0, NOW()),
       (gen_random_uuid(), (select id from product where name like 'iPhone 13'), (select id from seller where name like 'MediaMarkt'), 4799. , 'https://mediamarkt.pl/pl/product/_smartfon-apple-iphone-13-512gb-rozowy-mlqe3pma-1455336.html', 0, NOW()),
       (gen_random_uuid(), (select id from product where name like 'PS5'), (select id from seller where name like 'Euro-net'), 2689. , 'https://www.euro.com.pl/konsole-playstation-5/sony-playstation-5-ps5-slim-z-napedem-1tb-.bhtml', 0, NOW()),
        (gen_random_uuid(), (select id from product where name like 'PS5'), (select id from seller where name like 'MediaMarkt'), 2699. , 'https://mediamarkt.pl/pl/product/_konsola-sony-playstation-5-slim-1tb-d-chassis-1479260.html', 0, NOW());



