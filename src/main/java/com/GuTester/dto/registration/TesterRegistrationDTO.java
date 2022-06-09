package com.GuTester.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TesterRegistrationDTO extends RegistrationDTO {
    List<String> devices;
    List<String> os;
    List<String> networks;
    List<String> mobileOperators;
}
