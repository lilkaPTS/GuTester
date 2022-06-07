package com.GuTester.controller.choiceInformation;

import com.GuTester.service.ChoiceInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/choiceInformation")
@RequiredArgsConstructor
public class ChoiceInformationController {

    private final ChoiceInformationService choiceInformationService;

    @GetMapping("/getAllDeviceNames")
    public List<String> getAllDeviceNames(){
        return choiceInformationService.getAllDeviceNames();
    }

    @GetMapping("/getAllMobileOperatorNames")
    public List<String> getAllMobileOperatorNames(){
        return choiceInformationService.getAllMobileOperatorNames();
    }

    @GetMapping("/getAllOSNames")
    public List<String> getAllOSNames(){
        return choiceInformationService.getAllOSNames();
    }

    @GetMapping("/getAllNetworkNames")
    public List<String> getAllNetworkNames(){
        return choiceInformationService.getAllNetworkNames();
    }
}
