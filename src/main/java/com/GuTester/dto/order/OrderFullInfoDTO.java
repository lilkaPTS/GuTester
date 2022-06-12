package com.GuTester.dto.order;


import com.GuTester.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFullInfoDTO extends CreateOrderDTO {

    private Long orderId;
    private Status status;
    private String adminComment;

}
