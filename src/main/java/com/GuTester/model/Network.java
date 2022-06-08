package com.GuTester.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "network")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Network {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "network_id")
    private Long networkId;

    @Column
    private String name;
}
