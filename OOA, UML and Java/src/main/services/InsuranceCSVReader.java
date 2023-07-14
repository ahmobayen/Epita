package main.services;

import main.datamodel.Insurance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsuranceCSVReader {


    /**
     * Read file from CSV for insurances
     * @return List<Insurance></Insurance>
     * @throws FileNotFoundException
     */
    public List<Insurance> readAll() throws FileNotFoundException {
        List<Insurance> insurances = new ArrayList<>();
        File file = new File("resources/insurances.csv");

        // read all the lines at once by this Files class function
        // Files.readAllLines(file.toPath());
        try (Scanner scanner = new Scanner(file);) {
            scanner.nextLine(); //skip the header line
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    String[] split = line.split(";");
                    Integer insuranceID = Integer.parseInt(split[0].trim());
                    String insuranceName = split[1].trim();
                    Insurance insurance = new Insurance(insuranceID, insuranceName);
                    insurances.add(insurance);

                } catch (Exception e) {
                    System.out.println("problem while dealing with this line: " + line);
                    e.printStackTrace(); // this is just printing the error, the while loop is not interrupted
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return insurances;
    }
}
