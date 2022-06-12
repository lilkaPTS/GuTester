package com.GuTester.dto.order;

import com.GuTester.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLowInfoDTO {
    private Long orderId;
    private String title;
    private Status status;
}
