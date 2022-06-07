package com.GuTester.service;

import com.GuTester.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;

    public Optional<String> createTester() {
        return Optional.ofNullable("1");
    }

    public Optional<String> createDeveloper() {
        return Optional.ofNullable("1");
    }
}
