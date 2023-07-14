package test;

import main.datamodel.Insurance;
import main.datamodel.Patient;
import main.services.InsuranceCSVReader;
import main.services.PatientCSVReader;

import java.io.FileNotFoundException;
import java.util.List;

public class TestOOP2 {
    public static void main(String[] args) throws FileNotFoundException {
        InsuranceCSVReader insuranceCSVReader = new InsuranceCSVReader();
        List<Insurance> insuranceList = insuranceCSVReader.readAll();
        System.out.println(insuranceList);

        PatientCSVReader patientCSVReader = new PatientCSVReader();
        List<Patient> patientList = patientCSVReader.readAll();
        System.out.println(patientList);
    }
}
