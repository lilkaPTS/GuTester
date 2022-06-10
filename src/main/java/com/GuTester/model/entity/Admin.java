package com.GuTester.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "admin_id")
    private Long adminId;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;
}
