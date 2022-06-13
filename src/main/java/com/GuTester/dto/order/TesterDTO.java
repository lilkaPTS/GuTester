package com.GuTester.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TesterDTO {
    private String name;
    private String email;
    private String device;
    private String mobileOperator;
    private List<String> networks;
    private Double rating;
}
