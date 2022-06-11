package com.GuTester.service.init;

import com.GuTester.dto.registration.DeveloperRegistrationDTO;
import com.GuTester.dto.registration.TesterRegistrationDTO;
import com.GuTester.model.entity.OS;
import com.GuTester.repository.*;
import com.GuTester.service.RegistrationService;
import com.GuTester.service.SelectService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InitUserService {

    private final static String DEVELOPER_NAMES = "Gina Hawkins;Joyce Lee;Ruth Smith;Felicia Jimenez;John Thompson;Patricia Shaw;Loretta Robertson;Ralph Harris;Robert Porter;Alice Allen;Sheila Stevenson;Eric Pena;Edward Summers;Vera Palmer;Lisa West;Eleanor Thompson;Janice Miller;Samuel Lewis;Julie Shaw;Cynthia Greer;Jeanette Coleman;Lorraine Walters;David Edwards;Sylvia Campbell;John Stanley;Gilbert Thomas;Betty Simpson;Jane Williams;Luis Garza;Virginia Washington;Stacy Weber;Harry Howard;Christian Nelson;Sandra Miller;John Schultz;Joy Farmer;Gregory Morgan;Florence Alexander;Joe Phillips;Cathy Hubbard;Christopher Marshall;Timothy Osborne;Susan Hopkins;Nancy Holloway;Karen Lawrence;Jessica Jones;Lance Brown;Brian Anderson;Timothy Blake;Natalie Franklin;Dana Johnston;Stephanie Davis;Connie Cole;Martin Chapman;Felicia Vasquez;Cindy Rhodes;Randall Perez;Joseph Jackson;Erica Webb;Pamela Manning;Justin Black;Cathy Smith;Lisa Holloway;Robert Clark;Michael Gilbert;John Stokes;Esther Ballard;Ruby Casey;Rose Flores;Robert Paul;Holly Munoz;Bertha Harris;John Hamilton;Clifton Smith;Megan Brown;Patricia Smith;Maria Boyd;Sharon Armstrong;Juan Scott;Franklin Gonzalez;Dorothy Carr;Herman Griffin;James Garrett;Thomas Phillips;Erin Morrison;Jane Medina;Leon Harris;William Wilkins;Cory Roberts;Kimberly Torres;Douglas Brooks;Eugene Brown;Nancy Hubbard;Jack Summers;Randall Johnson;Samuel White;Glenn Johnson;Rose Ray;Sarah Williams;Dorothy Ruiz";
    private final static String TESTER_NAMES = "Betty Thomas;Nicholas Knight;Mary Mann;William Foster;Connie Simpson;Jennifer Hughes;Lauren West;Juan Davis;James Olson;Mark Ryan;Amber Perez;John James;Judith Park;Travis Henry;Nicole Johnson;Floyd Burgess;Elaine Lewis;Tommy Armstrong;Olga Williams;Adam Brown;Richard Miller;Randall Harris;Anna Castro;Ronald Henderson;Doris Matthews;Joshua Taylor;Jose Baker;Julie Hamilton;Shannon Rodgers;Jessica Murray;Amy Newman;Katrina Fisher;Christina Frank;Brian Hopkins;Laura Weber;Donna Wolfe;Heidi Campbell;Kristin Steele;Cynthia Graham;Jean Butler;Herbert Klein;Fred Ramos;Lucille Gardner;Randy Davis;John Henry;Anthony James;Rebecca Hammond;James Walton;Linda Cole;Michael Rodriguez;Elizabeth Paul;Ruth Christensen;James Vasquez;Frank Davis;Jessica Grant;Sonia Garcia;Richard Shelton;Richard Owens;John Blake;Henry Dunn;James Chapman;Debra Matthews;Brittany Ortiz;Ana Johnson;Janet Copeland;Eva Cooper;Jeffrey Bell;Michael Gonzalez;Eric Lyons;Billie Williams;James Thomas;Michael Garrett;Norma Maxwell;Melissa Reeves;Stephen Walton;Susan Martin;John Horton;Ann Meyer;Laura Morgan;Albert Ward;Raymond Gonzales;Susan Harvey;Barbara Cook;Glen Smith;Gerald Anderson;Vanessa Larson;Matthew Brown;Andrea Sullivan;James Poole;Robert Spencer;Helen Thomas;James Davis;Adrian Riley;Eric Montgomery;Elizabeth Andrews;Penny Harris;Thomas Cook;Andrew Ortiz;Sherry Cruz;Danny Ortega";

    private final TesterRepository testerRepository;
    private final DeveloperRepository developerRepository;
    private final RegistrationService registrationService;
    private final SelectService selectService;
    private final DeviceRepository deviceRepository;
    private final DeviceManufacturerRepository deviceManufacturerRepository;

    public void initDevelopers() {
        if(developerRepository.findAll().size() < 99) {
            String[] names = DEVELOPER_NAMES.split(";");
            for (String s : names) {
                String name = s.replace(' ', '-');
                DeveloperRegistrationDTO dto = new DeveloperRegistrationDTO();
                dto.setEmail(name + "@mail.ru");
                dto.setName(name);
                dto.setPassword(name);
                dto.setRole("DEVELOPER");
                registrationService.createDeveloper(dto, true);
            }
        }
    }

    public void initTesters() {
        if (testerRepository.findAll().size() < 99) {
            List<String> devices = selectService.getAllDeviceNames();
            List<String> os = selectService.getAllOSNames();
            List<String> networks = selectService.getAllNetworkNames();
            List<String> operators = selectService.getAllMobileOperatorNames();

            String[] names = TESTER_NAMES.split(";");
            for (String s : names) {
                String name = s.replace(' ', '-');
                TesterRegistrationDTO dto = new TesterRegistrationDTO();
                dto.setEmail(name + "@gmail.com");
                dto.setName(name);
                dto.setPassword(name);
                dto.setRole("TESTER");
                dto.setDevices(Collections.singletonList(devices.get((int) (Math.random() * devices.size()))));
                String osForAdd = "";
                String deviceName = dto.getDevices().stream().findFirst().orElseThrow();
                OS osOfInstalledDevice = deviceRepository.getDeviceByDeviceManufacturerAndDeviceModel(
                        deviceManufacturerRepository.findDeviceManufacturerByName(StringUtils.substringBefore(deviceName, " ")),
                        StringUtils.substringAfter(deviceName, " ")).getOs();
                while (!osForAdd.equals(osOfInstalledDevice.getName() + " " + osOfInstalledDevice.getVersion())) {
                    osForAdd = os.get((int) (Math.random() * os.size()));
                }
                dto.setOs(Collections.singletonList(osForAdd));
                dto.setMobileOperators(Collections.singletonList(operators.get((int) (Math.random() * operators.size()))));
                int count = 0;
                while (count < 1) {
                    count = (int) (Math.random() * 3);
                }
                Set<String> networksForAdd = new HashSet<>();
                while (networksForAdd.size() != count) {
                    networksForAdd.add(networks.get((int) (Math.random() * networks.size())));
                }
                dto.setNetworks(new ArrayList<>(networksForAdd));
                registrationService.createTester(dto, true);
            }
        }
    }

}

