package com.GuTester.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TesterRegistrationDTO extends RegistrationDTO {
    String[] devices;
    String[] os;
    String[] networks;
    String[] mobileOperators;
}
