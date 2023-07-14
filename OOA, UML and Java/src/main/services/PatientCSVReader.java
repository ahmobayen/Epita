package main.services;

import main.datamodel.Patient;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PatientCSVReader {
    /**
     * Read file from CSV for patients
     * @return List<Patient></Patient>
     * @throws FileNotFoundException
     */
    public List<Patient> readAll() throws FileNotFoundException {
        List<Patient> patients = new ArrayList<>();
        File file = new File("resources/patients.csv");

        // read all the lines at once by this Files class function
        // Files.readAllLines(file.toPath());
        try (Scanner scanner = new Scanner(file);) {
            scanner.nextLine(); //skip the header line
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    String[] split = line.split(";");
                    String pat_num_HC = split[0].trim();
                    String pat_lastname = split[1].trim();
                    String pat_firstname = split[2].trim();
                    String pat_address = split[3].trim();
                    String pat_tel = split[4].trim();
                    String pat_insurance_id = split[5].trim();
                    Date pat_sub_date=new SimpleDateFormat("dd/MM/yyyy").parse(split[6].trim());


                    Patient patient = new Patient(pat_num_HC, pat_lastname, pat_firstname, pat_address, pat_tel,
                            pat_insurance_id, pat_sub_date);
                    patients.add(patient);

                } catch (Exception e) {
                    System.out.println("problem while dealing with this line: " + line);
                    e.printStackTrace(); // this is just printing the error, the while loop is not interrupted
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return patients;
    }

}
