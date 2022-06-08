package com.GuTester.service;

import com.GuTester.dto.registration.TesterRegistrationDTO;
import com.GuTester.enums.Role;
import com.GuTester.enums.Status;
import com.GuTester.model.ConfirmationCode;
import com.GuTester.model.Tester;
import com.GuTester.model.User;
import com.GuTester.repository.ConfirmationCodeRepository;
import com.GuTester.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final EmailService emailService;

    public Optional<String> createTester() {
        return Optional.ofNullable("1");
    }

    public Optional<String> createDeveloper() {
        return Optional.ofNullable("1");
    }

    public Boolean createTester(TesterRegistrationDTO registrationDTO) {
        if (isPresentEmail(registrationDTO.getEmail())) {
            return false;
        }
        User user = new User();
        user.setEmail(registrationDTO.getEmail());
        user.setName(registrationDTO.getName());
        user.setPassword(new BCryptPasswordEncoder(12).encode(registrationDTO.getPassword()));
        user.setRole(Role.TESTER);
        user.setStatus(Status.CONFIRMATION);

        if(!registrationDTO.getEmail().isBlank()) { //тут должны быть проверки
            System.out.println(user);
            System.out.println(userRepository.save(user));
            //sendConfirmationCode(registrationDTO.getEmail());
            return true;
        } else {
            return false;
        }
    }

    public Boolean isPresentEmail(String email) {
        return false;
    }

    public User updateStatus(Long userId, Status status) {
        User user = userRepository.getById(userId);
        user.setStatus(status);
        return userRepository.save(user);
    }

    public Boolean checkConfirmationCode(String email, String code) {
        ConfirmationCode confirmationCode = confirmationCodeRepository.getConfirmationCodeByEmail(email);
        User user = userRepository.findByEmail(email);
        if (confirmationCode != null && confirmationCode.getCode().equals(code)) {
            userRepository.save(updateStatus(user.getId(), Status.ACTIVE));
            return true;
        }
        return false;
    }

    public void sendConfirmationCode(String email) {
        Thread thread = new Thread(() -> {
            ConfirmationCode code = new ConfirmationCode();
            code.setCode(generateConfirmationCode(10));
            code.setEmail(email);
            emailService.sentEmail(code.getEmail(), code.getCode());
            if(confirmationCodeRepository.existByEmail(email)) {
                confirmationCodeRepository.updateCode(code.getEmail(), code.getCode());
            } else {
                confirmationCodeRepository.save(code);
            }
        });
        thread.start();
    }

    private String generateConfirmationCode(Integer codeLength) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            int number = (int) (Math.random()*10);
            code.append(number%2 == 0 ? String.valueOf((char) ((Math.random()*26)+65)) : String.valueOf(number));
        }
        return code.toString();
    }
}
