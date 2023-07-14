package test;

import main.datamodel.Patient;
import main.services.PatientCSVReader;
import main.services.PatientDAO;

import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class TestJDB2 {
    public static void main(String[] args) throws FileNotFoundException, ParseException, SQLException {
        //Create Table
        TestJDB1.test();

        // read Files
        PatientCSVReader patientCSVReader = new PatientCSVReader();
        List<Patient> patientList = patientCSVReader.readAll();

        // Insert Record to DB
        PatientDAO patientDAO = new PatientDAO(DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"));
        for (Patient patient : patientList){
            patientDAO.create(patient);
        }

        //Search Records From DB
        List<Patient> patientListFromDB = patientDAO.readAll();
        for (Patient patient : patientListFromDB){
            System.out.println(patient);
        }

        // Update name Records From DB
        for (Patient patient : patientListFromDB){
            patient.setPat_firstname("amir");
            patientDAO.update(patient.getPat_num_HC(), patient);
        }

        // Show Updated Result
        List<Patient> updateList = patientDAO.readAll();
        for (Patient patient : updateList){
            System.out.println(patient);
        }

        // Delete And Show remained Results
        for (Patient patient : updateList){
            patientDAO.delete(patient.getPat_num_HC());
        }

        List<Patient> afterDeleteList = patientDAO.readAll();
        System.out.println("-------- This is after delete: --------");
        for (Patient patient : afterDeleteList){
            System.out.println(patient);
        }
    }
}
