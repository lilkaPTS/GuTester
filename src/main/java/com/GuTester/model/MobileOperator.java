package com.GuTester.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mobile_operator")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileOperator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "mobile_operator_id")
    private Long mobileOperatorId;

    @Column
    private String name;
}
