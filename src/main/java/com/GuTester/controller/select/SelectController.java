package com.GuTester.controller.select;

import com.GuTester.service.SelectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/SelectInformation")
@RequiredArgsConstructor
public class SelectController {

    private final SelectService SelectInformationService;

    @GetMapping("/getAllDeviceNames")
    public List<String> getAllDeviceNames(){
        return SelectInformationService.getAllDeviceNames();
    }

    @GetMapping("/getAllMobileOperatorNames")
    public List<String> getAllMobileOperatorNames(){
        return SelectInformationService.getAllMobileOperatorNames();
    }

    @GetMapping("/getAllOSNames")
    public List<String> getAllOSNames(){
        return SelectInformationService.getAllOSNames();
    }

    @GetMapping("/getAllNetworkNames")
    public List<String> getAllNetworkNames(){
        return SelectInformationService.getAllNetworkNames();
    }
}
