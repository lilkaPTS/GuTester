package com.GuTester.controller.totest;

import com.GuTester.repository.DeviceManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private DeviceManufacturerRepository deviceManufacturerRepository;

    @GetMapping("/test")
    public List<String> greeting() {
        return deviceManufacturerRepository.getAllNames();
    }

}
