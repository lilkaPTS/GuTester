package com.GuTester.controller.auxiliary;

import com.GuTester.service.init.InitUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/init")
@RequiredArgsConstructor
public class InitController {

    private final InitUserService initUserService;

    @GetMapping(value = "/fillInByDevelopers")
    public void fillInByDevelopers() {
        initUserService.initDevelopers();
    }

    @GetMapping(value = "/fillInByTesters")
    public void fillInByTesters() {
        initUserService.initTesters();
    }
}
