package main.services;

import main.datamodel.Prescription;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrescriptionCSVReader {

    /**
     * Read file from CSV for Prescription
     *
     * @return List<Patient></Patient>
     * @throws FileNotFoundException
     */
    public List<Prescription> readAll() throws FileNotFoundException {
        List<Prescription> prescriptions = new ArrayList<>();
        File file = new File("resources/prescriptions.csv");

        // read all the lines at once by this Files class function
        // Files.readAllLines(file.toPath());
        try (Scanner scanner = new Scanner(file);) {
            scanner.nextLine(); //skip the header line
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    String[] split = line.split(";");
                    String presc_id = split[0].trim();
                    String presc_ref_pat = split[1].trim();
                    String presc_code = split[2].trim();
                    String presc_days = split[3].trim();
                    Prescription prescription = new Prescription(presc_id, presc_ref_pat, presc_code, presc_days);
                    prescriptions.add(prescription);

                } catch (Exception e) {
                    System.out.println("problem while dealing with this line: " + line);
                    e.printStackTrace(); // this is just printing the error, the while loop is not interrupted
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return prescriptions;
    }
}

