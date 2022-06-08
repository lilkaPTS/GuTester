package com.GuTester.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "os")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "os_id")
    private Long osId;

    @Column
    private String name;

    @Column
    private String version;
}
