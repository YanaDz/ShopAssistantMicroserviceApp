INSERT INTO product (id, name, firm, product_version, product_type, version, created)
VALUES (gen_random_uuid(), 'iPhone 13', 'Apple', '13', 'SMARTPHONES', 1, NOW()),
       (gen_random_uuid(), 'Galaxy S21', 'Samsung', 'S21', 'SMARTPHONES', 1, NOW()),
       (gen_random_uuid(), 'MacBook Air', 'Apple', 'M1', 'COMPUTERS', 1, NOW()),
       (gen_random_uuid(), 'ThinkPad X1 Carbon', 'Lenovo', 'Gen 9', 'COMPUTERS', 1, NOW()),
       (gen_random_uuid(), 'Dyson V11', 'Dyson', 'V11', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Roomba i7', 'iRobot', 'i7', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Instant Pot Duo', 'Instant Pot', 'Duo', 'KITCHEN_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Vitamix 5200', 'Vitamix', '5200', 'KITCHEN_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Dell XPS 13', 'Dell', '9310', 'COMPUTERS', 1, NOW()),
       (gen_random_uuid(), 'Surface Pro 7', 'Microsoft', 'Pro 7', 'COMPUTERS', 1, NOW()),
       (gen_random_uuid(), 'OnePlus 9', 'OnePlus', '9', 'SMARTPHONES', 1, NOW()),
       (gen_random_uuid(), 'Pixel 6', 'Google', '6', 'SMARTPHONES', 1, NOW()),
       (gen_random_uuid(), 'PS5', 'Sony', 'PlayStation 5', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'AirPods Pro', 'Apple', 'Pro', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'KitchenAid Mixer', 'KitchenAid', 'Artisan', 'KITCHEN_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Ninja Blender', 'Ninja', 'BL770', 'KITCHEN_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'HP Spectre x360', 'HP', 'x360', 'COMPUTERS', 1, NOW()),
       (gen_random_uuid(), 'Asus ROG Strix', 'Asus', 'G15', 'COMPUTERS', 1, NOW()),
       (gen_random_uuid(), 'Galaxy Tab S7', 'Samsung', 'S7', 'COMPUTERS', 1, NOW()),
       (gen_random_uuid(), 'iPad Pro', 'Apple', 'M1', 'COMPUTERS', 1, NOW()),
       (gen_random_uuid(), 'Sony Bravia X90J', 'Sony', 'X90J', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'LG OLED CX', 'LG', 'CX', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Bose QuietComfort 35', 'Bose', '35', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Jabra Elite 75t', 'Jabra', '75t', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Breville Barista Express', 'Breville', 'Express', 'KITCHEN_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Cuisinart Coffee Maker', 'Cuisinart', 'DCC-3200', 'KITCHEN_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Nest Thermostat', 'Google', 'Thermostat', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Ring Doorbell', 'Ring', '3', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Eufy RoboVac 11S', 'Eufy', '11S', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'TP-Link Archer A7', 'TP-Link', 'A7', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Amazon Echo Dot', 'Amazon', '4th Gen', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Sony WH-1000XM4', 'Sony', 'XM4', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Google Nest Hub', 'Google', 'Hub', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Logitech MX Master 3', 'Logitech', 'Master 3', 'COMPUTERS', 1, NOW()),
       (gen_random_uuid(), 'Canon EOS R5', 'Canon', 'R5', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Nikon Z6 II', 'Nikon', 'Z6 II', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Brother HL-L2395DW', 'Brother', '2395DW', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'KitchenAid Toaster', 'KitchenAid', 'KMT4116CU', 'KITCHEN_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Hamilton Beach Microwave', 'Hamilton Beach', 'P100N30ALS3A', 'KITCHEN_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Anova Sous Vide', 'Anova', 'Nano', 'KITCHEN_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Apple Watch Series 6', 'Apple', 'Series 6', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Samsung Galaxy Watch 4', 'Samsung', 'Watch 4', 'HOME_APPLIANCE', 1, NOW()),
       (gen_random_uuid(), 'Huawei P40 Pro', 'Huawei', 'P40 Pro', 'SMARTPHONES', 1, NOW()),
       (gen_random_uuid(), 'LG Wing', 'LG', 'Wing', 'SMARTPHONES', 1, NOW()),
       (gen_random_uuid(), 'Xiaomi Mi 11', 'Xiaomi', '11', 'SMARTPHONES', 1, NOW()),
       (gen_random_uuid(), 'Realme GT', 'Realme', 'GT', 'SMARTPHONES', 1, NOW()),
       (gen_random_uuid(), 'Oppo Find X3', 'Oppo', 'X3', 'SMARTPHONES', 1, NOW());

INSERT INTO seller (id, name, search_url, version, created)
VALUES (gen_random_uuid(), 'Euro-net', 'https://www.euro.com.pl/search.bhtml?keyword=', 1, NOW()),
       (gen_random_uuid(), 'MediaMarkt', 'https://mediamarkt.pl/pl/search.html?query=', 1, NOW());


INSERT INTO purchase (id, product_id, seller_id, price, product_url, version, created)
VALUES (gen_random_uuid(), '082771dc-15ec-49b8-9096-4fcb98cc0462', 'c7a338b2-57cc-41d0-9a65-a8ebf22d4857', 2099., 'https://www.euro.com.pl/odkurzacze-pionowe/dyson-v11.bhtml', 1, NOW()),
       (gen_random_uuid(), '082771dc-15ec-49b8-9096-4fcb98cc0462', '821116c4-ee8f-4108-9b93-d9c19da970ab', 2347., 'https://mediamarkt.pl/pl/product/_odkurzacz-bezprzewodowy-dyson-v11-1477006.html', 1, NOW()),
       (gen_random_uuid(), 'c5046109-a6ba-479e-86e0-bd7de6308fe1', 'c7a338b2-57cc-41d0-9a65-a8ebf22d4857', 4599. , 'https://www.euro.com.pl/telefony-komorkowe/apple-iphone-13-512gb-niebieski.bhtml', 1, NOW()),
       (gen_random_uuid(), 'c5046109-a6ba-479e-86e0-bd7de6308fe1', '821116c4-ee8f-4108-9b93-d9c19da970ab', 4799. , 'https://mediamarkt.pl/pl/product/_smartfon-apple-iphone-13-512gb-rozowy-mlqe3pma-1455336.html', 1, NOW()),
       (gen_random_uuid(), 'ef8c0b2d-f1ba-4cc1-8405-a4015e23327a', 'c7a338b2-57cc-41d0-9a65-a8ebf22d4857', 2689. , 'https://www.euro.com.pl/konsole-playstation-5/sony-playstation-5-ps5-slim-z-napedem-1tb-.bhtml', 1, NOW()),
        (gen_random_uuid(), 'ef8c0b2d-f1ba-4cc1-8405-a4015e23327a', '821116c4-ee8f-4108-9b93-d9c19da970ab', 2699. , 'https://mediamarkt.pl/pl/product/_konsola-sony-playstation-5-slim-1tb-d-chassis-1479260.html', 1, NOW());



