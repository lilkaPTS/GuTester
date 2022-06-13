package com.GuTester.controller.account.order;

import com.GuTester.dto.order.InvitationActionDTO;
import com.GuTester.dto.order.ApprovedTestersDTO;
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
    public Boolean createOrder(@RequestBody CreateOrderDTO dto) {
        return orderService.createOrder(dto);
    }

    @PostMapping(value = "/reopenOrder")
    public Boolean reopenOrder(@RequestBody CreateOrderDTO dto) {
        return orderService.reopenOrder(dto);
    }

    @PostMapping(value = "/setApprovedTesters")
    public Boolean setApprovedTesters(@RequestBody ApprovedTestersDTO dto) {
        return orderService.setApprovedTesters(dto);
    }

    @PostMapping(value = "/acceptInvitation")
    private Boolean acceptInvitation(@RequestBody InvitationActionDTO dto) {
        return false;
    }

    @PostMapping(value = "/declineInvitation")
    private Boolean declineInvitation(@RequestBody InvitationActionDTO dto) {
        return false;
    }

    @GetMapping(value = "/getOrderById")
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
