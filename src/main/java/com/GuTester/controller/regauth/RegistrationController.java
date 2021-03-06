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
    public Boolean createTester(@RequestBody TesterRegistrationDTO dto) {
        return registrationService.createTester(dto, false);
    }

    @PostMapping(value = "/createDeveloper")
    public Boolean createDeveloper(@RequestBody DeveloperRegistrationDTO dto) {
        return registrationService.createDeveloper(dto, false);
    }

    @PutMapping("/checkConfirmation")
    public Boolean checkConfirmationCode(String email, String code) {
        return registrationService.checkConfirmationCode(email, code);
    }

    @PostMapping("/sendConfirmation")
    public void sendConfirmationCode(@RequestBody String email) {
        registrationService.sendConfirmationCode(email);
    }

    @PostMapping("/editTesterAccount")
    public Boolean editTesterAccount() {
        return false;
    }

    @PostMapping("/editDeveloperAccount")
    public Boolean editDeveloperAccount() {
        return false;
    }
}
