package com.GuTester.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tester")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "tester_id")
    private Long testerId;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @OneToOne
    @JoinColumn(name = "os_id")
    private OS os;

    @OneToOne
    @JoinColumn(name = "mobile_operator_id")
    private MobileOperator mobileOperator;

    @Column
    private String rating;

    @ManyToMany
    @JoinTable(
            name = "tester_network",
            joinColumns = @JoinColumn(name = "tester_id"),
            inverseJoinColumns = @JoinColumn(name = "network_id"))
    private List<Network> networks;
}
