package main.services;

import main.datamodel.Medication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicationDAO {

    private Connection connection;

    public MedicationDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Medication medication) {
        try {
            String query = "INSERT INTO medications (medication_code, medication_name, medication_comment) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, medication.getMedicationCode());
            preparedStatement.setString(2, medication.getMedicationName());
            preparedStatement.setString(3, medication.getMedicationComment());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Medication read(String medicationCode) {
        Medication medication = null;
        try {
            String query = "SELECT * FROM medications WHERE medication_code=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, medicationCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                medication = new Medication(resultSet.getString("medication_code"),
                        resultSet.getString("medication_name"),
                        resultSet.getString("medication_comment"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medication;
    }

    public List<Medication> readAll() {
        List<Medication> medications = new ArrayList<>();
        try {
            String query = "SELECT * FROM medications";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Medication medication = new Medication(resultSet.getString("code"), resultSet.getString("name"), resultSet.getString("comment"));
                medications.add(medication);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medications;
    }

    public void update(Medication medication) {
        try {
            String query = "UPDATE medications SET medication_name=?, medication_comment=? WHERE medication_code=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, medication.getMedicationName());
            preparedStatement.setString(2, medication.getMedicationComment());
            preparedStatement.setString(3, medication.getMedicationCode());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(String medicationCode) {
        try {
            String query = "DELETE FROM medications WHERE medication_code=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, medicationCode);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
