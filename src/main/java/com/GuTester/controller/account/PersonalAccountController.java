package com.GuTester.controller.account;

import com.GuTester.dto.order.OrderFullInfoDTO;
import com.GuTester.dto.order.OrderLowInfoDTO;
import com.GuTester.dto.order.TesterDTO;
import com.GuTester.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Access-Control-Request-Headers"})
@RequestMapping("/api/personal/account")
@RequiredArgsConstructor
public class PersonalAccountController {

    private final OrderService orderService;

    @GetMapping(value = "/getAllOrderLowInfoByDeveloperEmail")
    public List<OrderLowInfoDTO> getAllOrderLowInfoByDeveloperEmail(String developerEmail) {
        return orderService.getAllOrderLowInfoByDeveloperEmail(developerEmail);
    }

    @GetMapping(value = "/getAllOrderLowInfo")
    public List<OrderLowInfoDTO> getAllOrderLowInfo() {
        return orderService.getAllOrderLowInfo();
    }

    @GetMapping(value = "/getOrderFullInfoByOrderId")
    public OrderFullInfoDTO getOrderFullInfoByOrderId(Long orderId) {
        return orderService.getOrderFullInfoByOrderId(orderId);
    }

    @GetMapping(value = "/getAdminCommentByOrderId")
    public Map<String, String> getAdminCommentByOrderId(Long orderId) {
        return Collections.singletonMap("adminComment", orderService.getAdminCommentByOrderId(orderId));
    }

    @GetMapping(value = "/getUnapprovedTestersByOrderId")
    public List<TesterDTO> getUnapprovedTestersByOrderId(Long orderId) {
        return orderService.getUnapprovedTestersByOrderId(orderId);
    }

    @DeleteMapping(value = "/removeOrderByOrderId")
    public Boolean removeOrderByOrderId(Long orderId) {
        return orderService.removeOrderByOrderId(orderId);
    }


}
