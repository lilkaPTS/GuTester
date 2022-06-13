package com.GuTester.service;

import com.GuTester.dto.order.*;
import com.GuTester.enums.Status;
import com.GuTester.model.entity.*;
import com.GuTester.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final TesterRepository testerRepository;
    private final DeviceRepository deviceRepository;
    private final MobileOperatorRepository mobileOperatorRepository;
    private final NetworkRepository networkRepository;
    private final OSRepository osRepository;
    private final DeveloperRepository developerRepository;
    private final OrderRepository orderRepository;
    private final DeviceManufacturerRepository deviceManufacturerRepository;

    private Boolean setOrderFromCreateOrderDTO(Order order, CreateOrderDTO dto) {
        order.setDeveloper(developerRepository.findDeveloperByUser(
                userRepository.findByEmail(
                        dto.getDeveloperEmail())));
        if(
                order.getDeveloper() == null ||
                        dto.getSourceLink() == null ||
                        dto.getTitle() == null ||
                        dto.getDescription() == null ||
                        dto.getRequiredNumberOfTesters() == null
        ) {
            return false;
        }
        if(StringUtils.isNotBlank(dto.getOsName())) {
            order.setOsName(dto.getOsName());
        }
        order.setSourceLink(dto.getSourceLink());
        order.setTitle(dto.getTitle());
        order.setDescription(dto.getDescription());
        order.setRequiredNumberOfTesters(dto.getRequiredNumberOfTesters());
        order.setDeviceReleaseYearStart(dto.getDeviceReleaseYearStart());
        order.setDeviceReleaseYearEnd(dto.getDeviceReleaseYearEnd());
        order.setContactEmail(dto.getContactEmail());
        order.setStatus(Status.CONFIRMATION);
        order.setOrderCreationDate(new Date());
        order.setOsList(dto.getOsList()
                .stream()
                .map(os -> osRepository.getOSByNameAndVersion(
                        StringUtils.substringBefore(os, " "),
                        StringUtils.substringAfter(os, " ")
                ))
                .collect(Collectors.toList())
        );
        order.setDevices(dto.getDevices()
                .stream()
                .map(device -> deviceRepository.getDeviceByDeviceManufacturerAndDeviceModel(
                        deviceManufacturerRepository.findDeviceManufacturerByName(StringUtils.substringBefore(device, " ")),
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
        return true;
    }

    public Boolean createOrder(CreateOrderDTO dto) {
        Order order = new Order();
        Boolean setResult = setOrderFromCreateOrderDTO(order, dto);
        if(setResult) {
            orderRepository.save(order);
            return true;
        } else {
            return false;
        }

    }

    public Boolean reopenOrder(CreateOrderDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId()).orElse(null);
        if(order == null) {
            return false;
        }
        Boolean setResult = setOrderFromCreateOrderDTO(order, dto);
        if(setResult) {
            orderRepository.save(order);
            return true;
        } else {
            return false;
        }
    }

    public Boolean approveOrder(Long orderId) {
        Order order = orderRepository.getById(orderId);
        List<OS> osList = order.getOsList();
        List<Device> devices = order.getDevices();
        List<DeviceManufacturer> deviceManufacturers = order.getDeviceManufacturers();
        List<MobileOperator> mobileOperators = order.getMobileOperators();
        List<Network> networks = order.getNetworks();
        Network wifi = networks.contains(networkRepository.getNetworkByName("Wi-Fi")) ? networkRepository.getNetworkByName("Wi-Fi") : null;
        if(wifi!=null) networks.remove(wifi);
        int maxG = networks.stream().map(network -> network.getName().replace("G", "")).mapToInt(Integer::parseInt).max().orElse(-1);
        List<Tester> unapprovedTesters = testerRepository.findAllByOrder(
                order.getOrderId(),
                osList.size() != 0,
                deviceManufacturers.size() != 0,
                devices.size() != 0,
                mobileOperators.size() != 0
        );
        if(wifi!=null) {
            unapprovedTesters = unapprovedTesters.stream().filter(tester -> tester.getNetworks().contains(wifi)).collect(Collectors.toList());
        }
        if(maxG!=-1) {
            List<Tester> listToDelete = new ArrayList<>();
            for (Tester tester : unapprovedTesters) {
                tester.getNetworks().remove(wifi);
                if (tester.getNetworks().stream().map(network -> network.getName().replace("G", "")).mapToInt(Integer::parseInt).max().orElse(-1) < maxG) {
                    listToDelete.add(tester);
                }
            }
            listToDelete.forEach(unapprovedTesters::remove);
        }
        order.setUnapprovedTesters(unapprovedTesters);
        order.setStatus(Status.APPROVED);
        orderRepository.save(order);
        return true;
    }

    public Boolean rejectOrder(Long orderId, String adminComment) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) {
            return false;
        }
        order.setStatus(Status.REJECT);
        order.setAdminComment(adminComment);
        orderRepository.save(order);
        return true;
    }

    public List<OrderLowInfoDTO> getAllOrderLowInfoByDeveloperEmail(String email) {
        return orderListToOrderLowInfoDTOList(orderRepository.findAllByDeveloper(developerRepository.findDeveloperByUser(userRepository.findByEmail(email))));
    }

    public List<OrderLowInfoDTO> getAllOrderLowInfo() {
        return orderListToOrderLowInfoDTOList(orderRepository.findAll());
    }

    private List<OrderLowInfoDTO> orderListToOrderLowInfoDTOList(List<Order> orders) {
        List<OrderLowInfoDTO> result = new ArrayList<>();
        for(Order order : orders) {
            OrderLowInfoDTO lowInfoDTO = new OrderLowInfoDTO();
            lowInfoDTO.setOrderId(order.getOrderId());
            lowInfoDTO.setTitle(order.getTitle());
            lowInfoDTO.setStatus(order.getStatus());
            result.add(lowInfoDTO);
        }
        return result;
    }

    public OrderFullInfoDTO getOrderFullInfoByOrderId(Long orderId) {
        OrderFullInfoDTO result = new OrderFullInfoDTO();
        Order foundOrder = orderRepository.findById(orderId).orElse(null);
        if(foundOrder==null) {
            return null;
        }
        result.setOrderId(foundOrder.getOrderId());
        result.setStatus(foundOrder.getStatus());
        result.setAdminComment(foundOrder.getAdminComment());
        result.setSourceLink(foundOrder.getSourceLink());
        result.setTitle(foundOrder.getTitle());
        result.setDescription(foundOrder.getDescription());
        result.setRequiredNumberOfTesters(foundOrder.getRequiredNumberOfTesters());
        result.setDeviceReleaseYearStart(foundOrder.getDeviceReleaseYearStart());
        result.setDeviceReleaseYearEnd(foundOrder.getDeviceReleaseYearEnd());
        result.setContactEmail(foundOrder.getContactEmail());
        result.setOsList(foundOrder.getOsList().stream().map(os -> os.getName() + " " + os.getVersion()).collect(Collectors.toList()));
        result.setDevices(foundOrder.getDevices().stream().map(device -> device.getDeviceManufacturer().getName() + " " + device.getDeviceModel()).collect(Collectors.toList()));
        result.setDeviceManufacturers(foundOrder.getDeviceManufacturers().stream().map(DeviceManufacturer::getName).collect(Collectors.toList()));
        result.setMobileOperators(foundOrder.getMobileOperators().stream().map(MobileOperator::getName).collect(Collectors.toList()));
        result.setNetworks(foundOrder.getNetworks().stream().map(Network::getName).collect(Collectors.toList()));
        return result;
    }



    public String getAdminCommentByOrderId(Long orderId) {
        return orderRepository.findById(orderId).orElse(new Order()).getAdminComment();
    }

    public Boolean removeOrderByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order!=null) {
            orderRepository.delete(order);
            return true;
        } else {
            return false;
        }
    }

    public List<TesterDTO> getUnapprovedTestersByOrderId(Long orderId) {
        List<TesterDTO> result = new ArrayList<>();
        Order order = orderRepository.findById(orderId).orElseThrow(null);
        if(order == null) {
            return null;
        }
        List<Tester> foundUnapprovedTesters = order.getUnapprovedTesters();
        for(Tester foundTester: foundUnapprovedTesters) {
            TesterDTO dto = new TesterDTO();
            dto.setName(foundTester.getUser().getName());
            dto.setEmail(foundTester.getUser().getEmail());
            dto.setDevice(foundTester.getDevice().getName());
            dto.setMobileOperator(foundTester.getMobileOperator().getName());
            dto.setNetworks(foundTester.getNetworks().stream().map(Network::getName).collect(Collectors.toList()));
            dto.setRating(foundTester.getRating());
            result.add(dto);
        }
        return result;
    }

    public Boolean setApprovedTesters(ApprovedTestersDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId()).orElseThrow(null);
        if(order == null) {
            return false;
        }
        order.setApprovedTesters(dto.getEmails().stream()
                .map(email -> testerRepository.findTesterByUser(userRepository.findByEmail(email)))
                .collect(Collectors.toList())
        );
        order.setStatus(Status.ACTIVE);
        orderRepository.save(order);
        return true;
    }

}
