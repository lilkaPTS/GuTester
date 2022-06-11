package com.GuTester.controller.account.order;

import com.GuTester.dto.order.CreateOrderDTO;
import com.GuTester.model.entity.Order;
import com.GuTester.repository.OrderRepository;
import com.GuTester.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping(value = "/createOrder")
    public Boolean createTester(@RequestBody CreateOrderDTO dto) {
        return orderService.createOrder(dto);
    }


    @GetMapping(value = "/getOrderById")
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
