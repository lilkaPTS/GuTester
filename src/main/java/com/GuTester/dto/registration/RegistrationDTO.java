package com.GuTester.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class RegistrationDTO {
    private String email;
    private String name;
    private String password;
    private String role;
}
