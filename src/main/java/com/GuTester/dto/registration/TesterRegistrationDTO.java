package com.GuTester.dto.registration;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
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
