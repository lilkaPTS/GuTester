package com.GuTester.service;

import com.GuTester.model.Device;
import com.GuTester.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChoiceInformationService {

    private final DeviceManufacturerRepository deviceManufacturerRepository;
    private final DeviceRepository deviceRepository;
    private final MobileOperatorRepository mobileOperatorRepository;
    private final NetworkRepository networkRepository;
    private final OSRepository osRepository;

    public List<String> getAllDeviceNames() {
        return deviceRepository.getAllDevices().stream().map(device -> device.getDeviceManufacturer().getName() + " " + device.getDeviceModel()).collect(Collectors.toList());
    }

    public List<String> getAllMobileOperatorNames() {
        return mobileOperatorRepository.getAllNames();
    }

    public List<String> getAllOSNames() {
        return osRepository.getAllNames().stream().map(os -> os.getName() + " " + os.getVersion()).collect(Collectors.toList());
    }

    public List<String> getAllNetworkNames() {
        return networkRepository.getAllNames();
    }
}
