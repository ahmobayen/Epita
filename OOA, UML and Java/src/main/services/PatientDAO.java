package main.services;

import main.datamodel.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientDAO {
    private Connection connection;

    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Patient patient) throws SQLException {
        String query = "INSERT INTO patient (pat_num_HC, pat_lastname, pat_firstname, pat_address, pat_tel, pat_insurance_id, pat_sub_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, patient.getPat_num_HC());
        preparedStatement.setString(2, patient.getPat_lastname());
        preparedStatement.setString(3, patient.getPat_firstname());
        preparedStatement.setString(4, patient.getPat_address());
        preparedStatement.setString(5, patient.getPat_tel());
        preparedStatement.setString(6, patient.getPat_insurance_id());
        preparedStatement.setDate(7, new java.sql.Date(patient.getPat_sub_date().getTime()));
        preparedStatement.executeUpdate();
    }

    public Patient read(String pat_num_HC) throws SQLException {
        String query = "SELECT * FROM patient WHERE pat_num_HC = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pat_num_HC);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String pat_lastname = resultSet.getString("pat_lastname");
            String pat_firstname = resultSet.getString("pat_firstname");
            String pat_address = resultSet.getString("pat_address");
            String pat_tel = resultSet.getString("pat_tel");
            String pat_insurance_id = resultSet.getString("pat_insurance_id");
            Date pat_sub_date = resultSet.getDate("pat_sub_date");
            return new Patient(pat_num_HC, pat_lastname, pat_firstname, pat_address, pat_tel, pat_insurance_id, pat_sub_date);
        }

        return null;
    }

    public List<Patient> readAll() throws SQLException {
        String query = "SELECT * FROM patient";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Patient> patients = new ArrayList<>();

        while (resultSet.next()) {
            String pat_num_HC = resultSet.getString("pat_num_HC");
            String pat_lastname = resultSet.getString("pat_lastname");
            String pat_firstname = resultSet.getString("pat_firstname");
            String pat_address = resultSet.getString("pat_address");
            String pat_tel = resultSet.getString("pat_tel");
            String pat_insurance_id = resultSet.getString("pat_insurance_id");
            Date pat_sub_date = resultSet.getDate("pat_sub_date");
            patients.add(new Patient(pat_num_HC, pat_lastname, pat_firstname, pat_address, pat_tel, pat_insurance_id, pat_sub_date));
        }

        return patients;
    }

    public void update(String pat_num_HC, Patient patient) throws SQLException {
        String query = "UPDATE patient SET pat_lastname = ?, pat_firstname = ?, pat_address = ?, pat_tel = ?, pat_insurance_id = ?, pat_sub_date = ? WHERE pat_num_HC = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, patient.getPat_lastname());
        preparedStatement.setString(2, patient.getPat_firstname());
        preparedStatement.setString(3, patient.getPat_address());
        preparedStatement.setString(4, patient.getPat_tel());
        preparedStatement.setString(5, patient.getPat_insurance_id());
        preparedStatement.setDate(6, new java.sql.Date(patient.getPat_sub_date().getTime()));
        preparedStatement.setString(7, pat_num_HC);
        preparedStatement.executeUpdate();
    }

    public void delete(String pat_num_HC) throws SQLException {
        String query = "DELETE FROM patient WHERE pat_num_HC = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pat_num_HC);
        preparedStatement.executeUpdate();
    }
}