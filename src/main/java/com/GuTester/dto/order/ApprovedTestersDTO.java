package com.GuTester.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovedTestersDTO {
    private Long orderId;
    private List<String> emails;
}
