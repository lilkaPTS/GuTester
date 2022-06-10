package com.GuTester.repository;

import com.GuTester.model.entity.DeviceManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceManufacturerRepository extends JpaRepository<DeviceManufacturer, Long> {
    @Query(value = "SELECT name FROM device_manufacturer", nativeQuery = true)
    List<String> getAllNames();

    DeviceManufacturer findDeviceManufacturerByName(String name);
}
