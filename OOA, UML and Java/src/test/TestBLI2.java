package test;

import main.datamodel.Patient;
import main.services.PatientBLService;
import main.services.PatientCSVReader;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

public class TestBLI2 {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        PatientCSVReader patientCSVReader = new PatientCSVReader();
        List<Patient> patientList = patientCSVReader.readAll();
        System.out.println(PatientBLService.computeSeniorityByPatient(patientList));
    }
}
