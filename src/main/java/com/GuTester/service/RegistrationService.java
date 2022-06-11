package com.GuTester.service;

import com.GuTester.dto.registration.DeveloperRegistrationDTO;
import com.GuTester.dto.registration.RegistrationDTO;
import com.GuTester.dto.registration.TesterRegistrationDTO;
import com.GuTester.enums.Role;
import com.GuTester.enums.Status;
import com.GuTester.model.entity.ConfirmationCode;
import com.GuTester.model.entity.Developer;
import com.GuTester.model.entity.Tester;
import com.GuTester.model.entity.User;
import com.GuTester.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final TesterRepository testerRepository;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final EmailService emailService;
    private final DeviceRepository deviceRepository;
    private final MobileOperatorRepository mobileOperatorRepository;
    private final NetworkRepository networkRepository;
    private final OSRepository osRepository;
    private final DeveloperRepository developerRepository;
    private final DeviceManufacturerRepository deviceManufacturerRepository;

    public Boolean createDeveloper(DeveloperRegistrationDTO registrationDTO, boolean itIsInit) {
        if(!Role.DEVELOPER.equals(Role.valueOf(registrationDTO.getRole()))) {
            return false;
        }
        User user = createUser(registrationDTO, itIsInit);
        if(user == null) {
            return false;
        }
        User foundUser = userRepository.findByEmail(registrationDTO.getEmail());
        Developer foundDeveloper = new Developer();

        if(foundUser!=null) {
            foundDeveloper = developerRepository.findDeveloperByUser(foundUser);
        }
        double rating = 0.0;
        if(itIsInit) {
            while (rating < 3.5) {
                rating = Math.random() * 5;
            }
        }
        Developer developer = foundDeveloper != null ? foundDeveloper : new Developer();
        developer.setRating(rating);
        userRepository.save(user);
        developer.setUser(user);
        developerRepository.save(developer);
        if(!itIsInit) {
            sendConfirmationCode(registrationDTO.getEmail());
        }
        return true;
    }

    public Boolean createTester(TesterRegistrationDTO registrationDTO, boolean itIsInit) {
        if(!Role.TESTER.equals(Role.valueOf(registrationDTO.getRole()))) {
            return false;
        }
        User user = createUser(registrationDTO, itIsInit);
        if(user == null) {
            return false;
        }
        User foundUser = userRepository.findByEmail(registrationDTO.getEmail());
        Tester foundTester = new Tester();

        if(foundUser!=null) {
            foundTester = testerRepository.findTesterByUser(foundUser);
        }

        Tester tester = foundTester != null ? foundTester : new Tester();
        String deviceName = registrationDTO.getDevices().stream().findFirst().orElse("-");
        tester.setDevice(deviceRepository.getDeviceByDeviceManufacturerAndDeviceModel(
                        deviceManufacturerRepository.findDeviceManufacturerByName(StringUtils.substringBefore(deviceName, " ")),
                        StringUtils.substringAfter(deviceName, " "))
        );
        tester.setMobileOperator(mobileOperatorRepository.getMobileOperatorByName(registrationDTO.getMobileOperators().stream().findFirst().orElse("-")));
        tester.setNetworks(registrationDTO.getNetworks()
                        .stream()
                        .map(networkRepository::getNetworkByName)
                        .collect(Collectors.toList())
        );
        String os = registrationDTO.getOs().stream().findFirst().orElse("-");
        tester.setOs(osRepository.getOSByNameAndVersion(StringUtils.substringBefore(os, " "), StringUtils.substringAfter(os, " ")));

        double rating = 0.0;
        if(itIsInit) {
            while (rating < 3.5) {
                rating = Math.random() * 5;
            }
        }
        tester.setRating(rating);
        userRepository.save(user);
        tester.setUser(user);
        testerRepository.save(tester);
        if(!itIsInit) {
            sendConfirmationCode(registrationDTO.getEmail());
        }
        return true;
    }

    public Boolean isPresentUser(String email) {
        return userRepository.existUserByEmail(email);
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

    private User createUser(RegistrationDTO registrationDTO, boolean itIsInit) {
        if(
                Role.ADMIN.equals(Role.valueOf(registrationDTO.getRole())) ||
                StringUtils.isBlank(registrationDTO.getEmail()) ||
                isPresentUser(registrationDTO.getEmail())
        ) {
            return null;
        }
        User foundUser = userRepository.findByEmail(registrationDTO.getEmail());
        User user = foundUser != null ? foundUser : new User();
        user.setEmail(registrationDTO.getEmail());
        user.setName(registrationDTO.getName());
        user.setPassword(new BCryptPasswordEncoder(12).encode(registrationDTO.getPassword()));
        user.setRole(Role.valueOf(registrationDTO.getRole()));
        user.setStatus(itIsInit ? Status.ACTIVE : Status.CONFIRMATION);
        return user;
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
