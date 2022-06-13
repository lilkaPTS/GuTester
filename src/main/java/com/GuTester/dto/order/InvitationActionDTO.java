package com.GuTester.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationActionDTO {
    private Long orderId;
    private Long testerEmail;
}
