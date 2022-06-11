package com.GuTester.controller.admin;

import com.GuTester.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final OrderService orderService;

    @GetMapping(value = "/approveOrder")
    public Boolean approveOrder(Long orderId) {
        return orderService.approveOrder(orderId);
    }

    @GetMapping(value = "/rejectOrder")
    public Boolean rejectOrder(Long orderId, String adminComment) {
        return orderService.rejectOrder(orderId, adminComment);
    }
}
