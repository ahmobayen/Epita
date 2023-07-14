package main.services;

import main.datamodel.Medication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicationCSVReader {

    /**
     * Read file from CSV for Medication
     *
     * @return List<Patient></Patient>
     * @throws FileNotFoundException
     */
    public List<Medication> readAll() throws FileNotFoundException {
        List<Medication> medications = new ArrayList<>();
        File file = new File("resources/medications.csv");

        // read all the lines at once by this Files class function
        // Files.readAllLines(file.toPath());
        try (Scanner scanner = new Scanner(file);) {
            scanner.nextLine(); //skip the header line
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    String[] split = line.split(";");
                    String medication_code = split[0].trim();
                    String medication_name = split[1].trim();
                    String medication_comment = split[2].trim();
                    Medication medication = new Medication(medication_code, medication_name, medication_comment);
                    medications.add(medication);

                } catch (Exception e) {
                    System.out.println("problem while dealing with this line: " + line);
                    e.printStackTrace(); // this is just printing the error, the while loop is not interrupted
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return medications;
    }
}

