package com.GuTester.controller.regauth;

import com.GuTester.dto.registration.DeveloperRegistrationDTO;
import com.GuTester.dto.registration.TesterRegistrationDTO;
import com.GuTester.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/reg")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(value = "/createTester")
    public Boolean createTester(TesterRegistrationDTO dto) {
        return registrationService.createTester(dto);
    }

    @PostMapping(value = "/createDeveloper")
    public Boolean createDeveloper(DeveloperRegistrationDTO dto) {
        return registrationService.createDeveloper(dto);
    }

    @PutMapping("/checkConfirmation")
    public Boolean checkConfirmationCode(String code, String email) {
        return registrationService.checkConfirmationCode(email, code);
    }

    @PostMapping("/sendConfirmation")
    public void sendConfirmationCode(String email) {
        registrationService.sendConfirmationCode(email);
    }
}
