package com.GuTester.service;

import com.GuTester.dto.registration.LoginDTO;
import com.GuTester.model.User;
import com.GuTester.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    public ResponseEntity<?> authenticate(LoginDTO userDto) {
        Map<Object, Object> response = new HashMap<>();
        User user = userRepository.findByEmailWithStatusActive(userDto.getEmail());
        response.put("message", "Email и пароль не совпадают");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        if (!new BCryptPasswordEncoder(12).matches(userDto.getPassword(),user.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        response.put("email", userDto.getEmail());
        response.put("role", user.getRole());
        response.put("name", user.getName());
        response.remove("message");
        return ResponseEntity.ok(response);
    }
}
