package com.GuTester.service;

import com.GuTester.dto.order.OrderDTO;
import com.GuTester.enums.Status;
import com.GuTester.model.entity.Order;
import com.GuTester.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final TesterRepository testerRepository;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final EmailService emailService;
    private final DeviceRepository deviceRepository;
    private final MobileOperatorRepository mobileOperatorRepository;
    private final NetworkRepository networkRepository;
    private final OSRepository osRepository;
    private final DeveloperRepository developerRepository;
    private final OrderRepository orderRepository;
    private final DeviceManufacturerRepository deviceManufacturerRepository;

    public Boolean createOrder(@RequestBody OrderDTO dto) {
        Order order = new Order();
        order.setDeveloper(developerRepository.findDeveloperByUser(
                            userRepository.findByEmail(
                                dto.getDeveloperEmail())));
        order.setOsName(StringUtils.substringBefore(dto.getOsName(), " "));
        order.setSourceLink(dto.getSourceLink());
        order.setTitle(dto.getTitle());
        order.setDescription(dto.getDescription());
        order.setRequiredNumberOfTesters(dto.getRequiredNumberOfTesters());
        order.setDeviceReleaseYearStart(dto.getDeviceReleaseYearStart());
        order.setDeviceReleaseYearEnd(dto.getDeviceReleaseYearEnd());
        order.setStatus(Status.CONFIRMATION);
        order.setOrderCreationDate(new Date());
        order.setDevices(dto.getDevices()
                .stream()
                .map(device -> deviceRepository.getDeviceByDeviceModel(
                        StringUtils.substringAfter(device, " ")))
                .collect(Collectors.toList())
        );
        order.setDeviceManufacturers(dto.getDeviceManufacturers()
                .stream()
                .map(deviceManufacturerRepository::findDeviceManufacturerByName)
                .collect(Collectors.toList())
        );
        order.setMobileOperators(dto.getMobileOperators()
                .stream()
                .map(mobileOperatorRepository::getMobileOperatorByName)
                .collect(Collectors.toList())
        );
        order.setNetworks(dto.getNetworks()
                .stream()
                .map(networkRepository::getNetworkByName)
                .collect(Collectors.toList())
        );
        //
        // need search unapproved testers hear
        //
        System.out.println(order);
        orderRepository.save(order);
        return true;
    }
}
