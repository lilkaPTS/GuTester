package com.GuTester.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class RegistrationDTO {
    private String email;
    private String name;
    private String password;
    private String role;
}
