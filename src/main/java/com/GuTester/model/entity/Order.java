package com.GuTester.model.entity;

import com.GuTester.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "orders_id")
    private Long OrderId;

    @OneToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @Column(name = "os_name")
    private String osName;

    @Column(name = "source_link")
    private String sourceLink;

    @Column
    private String title;

    @Column
    private String description;

    @Column(name = "required_number_of_testers")
    private Integer requiredNumberOfTesters;

    @Column(name = "device_release_year_start")
    private Integer deviceReleaseYearStart;

    @Column(name = "device_release_year_end")
    private Integer deviceReleaseYearEnd;

    @Column(name = "contact_email")
    private String contactEmail;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "orders_creation_date")
    private Date orderCreationDate;

    @Column(name = "admin_comment")
    private String adminComment;

    @ManyToMany
    @JoinTable(name = "orders_os",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "os_id"))
    private List<OS> osList;

    @ManyToMany
    @JoinTable(name = "orders_device",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id"))
    private List<Device> devices;

    @ManyToMany
    @JoinTable(name = "orders_device_manufacturer",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "device_manufacturer_id"))
    private List<DeviceManufacturer> deviceManufacturers;

    @ManyToMany
    @JoinTable(name = "orders_mobile_operator",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "mobile_operator_id"))
    private List<MobileOperator> mobileOperators;

    @ManyToMany
    @JoinTable(name = "orders_network",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "network_id"))
    private List<Network> networks;



    @ManyToMany
    @JoinTable(name = "orders_unapproved_tester",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "tester_id"))
    private List<Tester> unapprovedTesters;

    @ManyToMany
    @JoinTable(name = "orders_approved_tester",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "tester_id"))
    private List<Tester> approvedTesters;
}
