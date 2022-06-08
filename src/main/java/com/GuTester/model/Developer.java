package com.GuTester.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "developer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "developer_id")
    private Long developerId;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;

    @Column
    private String rating;
}