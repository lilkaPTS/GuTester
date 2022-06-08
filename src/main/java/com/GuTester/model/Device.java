package com.GuTester.model;

import com.GuTester.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "device")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "device_id")
    private Long deviceId;

    @Column(name = "order_link")
    private String orderLink;

    @OneToOne
    @JoinColumn(name = "device_manufacturer_id")
    private DeviceManufacturer deviceManufacturer;

    @Column(name = "device_model")
    private String deviceModel;

    @OneToOne
    @JoinColumn(name = "os_id")
    private OS os;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
