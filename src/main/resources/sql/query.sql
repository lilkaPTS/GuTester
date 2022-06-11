SELECT tester_id,d.device_model, os.name FROM tester join device d on tester.device_id = d.device_id join os on tester.os_id = os.os_id  WHERE
    EXISTS (SELECT FROM os WHERE tester.os_id = os.os_id AND os.name = (SELECT os_name FROM orders WHERE orders_id = 5)) -- отбор тестировщиков с нужной операционной системой
            AND EXISTS (SELECT FROM orders_device_manufacturer odm WHERE orders_id = 5 AND odm.device_manufacturer_id = (select d.device_manufacturer_id from device d where d.device_id = tester.device_id)) -- отбор тестировщиков с нужным производителем
