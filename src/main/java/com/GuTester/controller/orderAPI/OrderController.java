package com.GuTester.controller.orderAPI;

import com.GuTester.dto.order.OrderDTO;
import com.GuTester.model.entity.Order;
import com.GuTester.repository.OrderRepository;
import com.GuTester.repository.TesterRepository;
import com.GuTester.repository.UserRepository;
import com.GuTester.service.init.InitUserService;
import com.GuTester.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final TesterRepository testerRepository;
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final InitUserService initUserService;
    private final OrderRepository orderRepository;

    @PostMapping(value = "/createOrder")
    public Boolean createTester(@RequestBody OrderDTO dto) {
        return orderService.createOrder(dto);
    }

    @GetMapping(value = "/fillInByDevelopers")
    public void fillInByDevelopers() {
        initUserService.initDevelopers();
    }

    @GetMapping(value = "/fillInByTesters")
    public void fillInByTesters() {
        initUserService.initTesters();
    }

    @GetMapping(value = "/getOrderById")
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
