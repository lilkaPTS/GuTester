package com.GuTester.repository;

import com.GuTester.model.entity.Device;
import com.GuTester.model.entity.DeviceManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query(value = "select * from device", nativeQuery = true)
    List<Device> getAllDevices();

    Device getDeviceByDeviceManufacturerAndDeviceModel(DeviceManufacturer deviceManufacturer, String deviceModel);
}
