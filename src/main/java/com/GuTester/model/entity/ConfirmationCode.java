package com.GuTester.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "confirmation_codes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "confirmation_code_id")
    private Long confirmationCodeId;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String email;
}
