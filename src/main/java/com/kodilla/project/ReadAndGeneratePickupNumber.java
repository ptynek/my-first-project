package com.kodilla.project;


import com.opencsv.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class ReadAndGeneratePickupNumber {

    public Set<DriverAndLicencePlate> readFile (String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        Path path = Paths.get(file.getPath());

        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(String.valueOf(path))).build();

            return reader.readAll().stream().map(data -> {
                DriverAndLicencePlate driverAndLicencePlate = new DriverAndLicencePlate();
                driverAndLicencePlate.setNameAndSurname(data[0]);
                driverAndLicencePlate.setLicencePlate(data[1]);
                return driverAndLicencePlate;
            }).collect(Collectors.toSet());

        } catch (Exception e){
            System.out.println(e);
        }
        return new HashSet<>();
    }

    public String pickUpNumber() {

        String pickUpNumber = "";

        pickUpNumber = RandomStringUtils.randomAlphanumeric(6);
        pickUpNumber.toUpperCase();

        return pickUpNumber;
    }

    public void createFile() throws Exception{
        ReadAndGeneratePickupNumber readAndGeneratePickupNumber = new ReadAndGeneratePickupNumber();
        Set<DriverAndLicencePlate> driverAndLicencePlateSet = readAndGeneratePickupNumber.readFile("drivers.csv");
        Map<PickUpNumber, DriverAndLicencePlate> driverAndLicencePlateMap = new HashMap<>();

        Iterator<DriverAndLicencePlate> iterator = driverAndLicencePlateSet.iterator();
        while(iterator.hasNext()) {
            DriverAndLicencePlate record = iterator.next();
            PickUpNumber pickUpNumber = new PickUpNumber(readAndGeneratePickupNumber.pickUpNumber().toUpperCase());
            driverAndLicencePlateMap.put(pickUpNumber, record);
        }
        String path = "C:\\Users\\tynek\\Desktop\\Projects\\my-project\\src\\main\\resources\\";

        try {

            File myFile = new File(path + "Numery zaladunkow.csv");
            myFile.createNewFile();
        } catch (Exception e){
            System.out.println(e);
        }

        try {
            FileWriter myWriter = new FileWriter(path + "Numery zaladunkow.csv");
            for(Map.Entry<PickUpNumber, DriverAndLicencePlate> entry :driverAndLicencePlateMap.entrySet()){
                myWriter.write(("Numer zaladunku: " + entry.getKey() + "\n"
                        + "Kierowca: " +  entry.getValue().getNameAndSurname() + "\n"
                        + "Pojazd: " + entry.getValue().getLicencePlate() + "\n\n"));
            }
            myWriter.close();
        } catch (Exception e){
            System.out.println(e);
        }

    }
}