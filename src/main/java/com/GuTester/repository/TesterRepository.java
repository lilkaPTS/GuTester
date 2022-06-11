package com.GuTester.repository;

import com.GuTester.model.entity.Tester;
import com.GuTester.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TesterRepository extends JpaRepository<Tester, Long> {

    Tester findTesterByUser(User user);

    @Query(value = "SELECT * FROM tester \n" +
            "/* отбор тестировщиков с нужным названием ос              */WHERE ( false or tester.os_id in (select os.os_id from os where os.name = (select os_name from orders where orders_id = :orderId)))" +
            "/* отбор тестировщиков с нужной версией ос                */AND (   :checkOsVersion or tester.os_id in (select os_id from orders_os where orders_id = :orderId))" +
            "/* отбор тестировщиков с нужным производителем устройства */AND (   :checkDeviceManufacturer or tester.device_id in (select d.device_id from device d where d.device_manufacturer_id in (select odm.device_manufacturer_id from orders_device_manufacturer odm where orders_id = :orderId)))" +
            "/* отбор тестировщиков с нужным устройством               */AND (   :checkDevice or tester.device_id in (select od.device_id from orders_device od where od.orders_id = :orderId))" +
            "/* отбор тестировщиков с нужным оператором СС             */AND (   :checkMobileOperator or tester.mobile_operator_id in (select omo.mobile_operator_id from orders_mobile_operator omo where omo.orders_id = :orderId));", nativeQuery = true)
    List<Tester> findAllByOrder(@Param("orderId") Long orderId,
                                @Param("checkOsVersion") Boolean notCheckOsVersion,
                                @Param("checkDeviceManufacturer") Boolean notCheckDeviceManufacturer,
                                @Param("checkDevice") Boolean notCheckDevice,
                                @Param("checkMobileOperator") Boolean notCheckMobileOperator
    );
}
