package com.GuTester.repository;

import com.GuTester.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query(value = "select * from device", nativeQuery = true)
    List<Device> getAllDevices();

    Device findAllByDeviceId(Long id);
}
