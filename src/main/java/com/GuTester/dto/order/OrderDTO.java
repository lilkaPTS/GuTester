package com.GuTester.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private String developerEmail;

    private String osName;

    private String sourceLink;

    private String title;

    private String description;

    private Integer requiredNumberOfTesters;

    private Integer deviceReleaseYearStart;

    private Integer deviceReleaseYearEnd;

    private String contactEmail;

    private List<String> osList;

    private List<String> devices;

    private List<String> deviceManufacturers;

    private List<String> mobileOperators;

    private List<String> networks;

}
