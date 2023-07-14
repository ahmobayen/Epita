package test;

import main.datamodel.Patient;
import main.services.PatientBLService;
import main.services.PatientCSVReader;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

public class TestBLI1 {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        PatientCSVReader patientCSVReader = new PatientCSVReader();
        List<Patient> patientList = patientCSVReader.readAll();

        Integer i = 0;
        for (Patient patient : patientList) {
            if (i == 5) {
                System.out.println(PatientBLService.computeSeniority(patient));
                break;
            }
            i++;
        }
    }
}
