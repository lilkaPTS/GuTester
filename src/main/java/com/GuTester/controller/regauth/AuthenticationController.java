package com.GuTester.controller.regauth;

import com.GuTester.dto.registration.LoginDTO;
import com.GuTester.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/reg")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginDTO request) {
        return authenticationService.authenticate(request);
    }

}
