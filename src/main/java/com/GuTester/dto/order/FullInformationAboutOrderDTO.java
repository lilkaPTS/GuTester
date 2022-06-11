package com.GuTester.dto.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullInformationAboutOrderDTO extends CreateOrderDTO {

    private Long orderId;

}
