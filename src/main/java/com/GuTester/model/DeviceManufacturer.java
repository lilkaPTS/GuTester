package com.GuTester.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "device_manufacturer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceManufacturer {

    @Id
    @GeneratedValue
    @Column(nullable = false, name = "device_manufacturer_id")
    private Long deviceManufacturerId;

    @Column
    private String name;

}
