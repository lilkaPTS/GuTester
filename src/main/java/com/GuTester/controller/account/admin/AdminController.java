package com.GuTester.controller.account.admin;

import com.GuTester.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final OrderService orderService;

    @PutMapping(value = "/approveOrder")
    public Boolean approveOrder(@RequestBody Long orderId) {
        return orderService.approveOrder(orderId);
    }

    @PutMapping(value = "/rejectOrder")
    public Boolean rejectOrder(Long orderId, String adminComment) {
        return orderService.rejectOrder(orderId, adminComment);
    }

    @PostMapping(value = "/createDevice")
    public Boolean createDevice() {
        return true;
    }
}
