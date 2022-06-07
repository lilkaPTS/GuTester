-- mobile_operator
insert into mobile_operator (name) values ('МТС');
insert into mobile_operator (name) values ('МегаФон');
insert into mobile_operator (name) values ('.TELE2.');
insert into mobile_operator (name) values ('Билайн');
insert into mobile_operator (name) values ('YOTA');
-- network
insert into network (name) values ('2G');
insert into network (name) values ('3G');
insert into network (name) values ('4G');
insert into network (name) values ('5G');
insert into network (name) values ('Wi-Fi');
-- device_manufacturer
insert into device_manufacturer (name) values ('Apple');
insert into device_manufacturer (name) values ('Samsung');
insert into device_manufacturer (name) values ('Xiaomi');
insert into device_manufacturer (name) values ('Huawei');
insert into device_manufacturer (name) values ('Sony');
insert into device_manufacturer (name) values ('HONOR');
insert into device_manufacturer (name) values ('POCO');
insert into device_manufacturer (name) values ('ASUS');
insert into device_manufacturer (name) values ('BQ');
insert into device_manufacturer (name) values ('OPPO');
-- os
insert into os (name, version) values ('ios', '9');
insert into os (name, version) values ('ios', '9.2');
insert into os (name, version) values ('ios', '9.3');
insert into os (name, version) values ('ios', '10');
insert into os (name, version) values ('ios', '10.1');
insert into os (name, version) values ('ios', '10.3');
insert into os (name, version) values ('ios', '11');
insert into os (name, version) values ('ios', '12');
insert into os (name, version) values ('ios', '13');
insert into os (name, version) values ('ios', '14');
insert into os (name, version) values ('ios', '15');
insert into os (name, version) values ('ios', '15.5');
insert into os (name, version) values ('Android', '5.0');
insert into os (name, version) values ('Android', '5.1');
insert into os (name, version) values ('Android', '6.0');
insert into os (name, version) values ('Android', '7.0');
insert into os (name, version) values ('Android', '7.1');
insert into os (name, version) values ('Android', '8.0');
insert into os (name, version) values ('Android', '8.1');
insert into os (name, version) values ('Android', '9');
insert into os (name, version) values ('Android', '10');
insert into os (name, version) values ('Android', '11');
insert into os (name, version) values ('Android', '12');
insert into os (name, version) values ('Android', '12.1');
-- device
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone 6s', 1, 2015, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone 7', 4, 2016, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone 8', 7, 2017, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone X', 8, 2018, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone XS', 8, 2018, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone XR', 8, 2018, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone 11', 9, 2019, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone 12', 10, 2020, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone 12 Mini', 10, 2020, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone 12 Pro', 10, 2020, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone 12 Pro Max', 10, 2020, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone 13', 11, 2021, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone 13 Mini', 11, 2021, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone 13 Pro', 11, 2021, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (1, 'iPhone 13 Pro Max', 11, 2021, 'ACTIVE');

insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (2, 'Galaxy A52', 22, 2021, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (2, 'Galaxy A12', 21, 2021, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (2, 'Galaxy M52', 22, 2021, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (2, 'Galaxy M32', 22, 2021, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (2, 'Galaxy A22S', 22, 2021, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (2, 'Galaxy S20 FE', 21, 2020, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (2, 'Galaxy S21 FE', 23, 2022, 'ACTIVE');

insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (3, '11 Lite', 22, 2021, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (3, '11T', 22, 2021, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (3, 'Redmi Note 10S', 22, 2021, 'ACTIVE');
insert into device (device_manufacturer_id, device_name, os_id, release_year, status) values (3, 'Redmi 9C', 21, 2020, 'ACTIVE');


