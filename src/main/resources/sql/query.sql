SELECT tester_id,d.device_model, os.name FROM tester join device d on tester.device_id = d.device_id join os on tester.os_id = os.os_id
WHERE ( false or tester.os_id in (select os.os_id from os where os.name = (select os_name from orders where orders_id = 1)))                                                                            -- отбор тестировщиков с нужным названием ос
  AND (   false or tester.os_id in (select os_id from orders_os where orders_id = 1))                                                                                                                     -- отбор тестировщиков с нужной версией ос
  AND (   false or tester.device_id in (select d.device_id from device d where d.device_manufacturer_id in (select odm.device_manufacturer_id from orders_device_manufacturer odm where orders_id = 1)))  -- отбор тестировщиков с нужным производителем
  AND (   false or tester.device_id in (select od.device_id from orders_device od where od.orders_id = 1))                                                                                                -- отбор тестировщиков с нужным устройством
  AND (   false or tester.mobile_operator_id in (select omo.mobile_operator_id from orders_mobile_operator omo where omo.orders_id = 1))                                                                  -- отбор тестировщиков с нужным оператором СС
;
