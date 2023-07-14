package main.services;

import main.datamodel.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {
    private Connection connection;

    public PrescriptionDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Prescription prescription) throws SQLException {
        String query = "INSERT INTO prescription (presc_id, presc_ref_pat, presc_code, presc_days) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, prescription.getPrescID());
        preparedStatement.setString(2, prescription.getPrescRefPat());
        preparedStatement.setString(3, prescription.getPrescCode());
        preparedStatement.setString(4, prescription.getPrescDays());
        preparedStatement.executeUpdate();
    }

    public Prescription read(String prescID) throws SQLException {
        String query = "SELECT * FROM prescription WHERE presc_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, prescID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String prescRefPat = resultSet.getString("presc_ref_pat");
            String prescCode = resultSet.getString("presc_code");
            String prescDays = resultSet.getString("presc_days");
            return new Prescription(prescID, prescRefPat, prescCode, prescDays);
        }

        return null;
    }

    public List<Prescription> readAll() throws SQLException {
        String query = "SELECT * FROM prescription";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Prescription> prescriptions = new ArrayList<>();

        while (resultSet.next()) {
            String prescID = resultSet.getString("presc_id");
            String prescRefPat = resultSet.getString("presc_ref_pat");
            String prescCode = resultSet.getString("presc_code");
            String prescDays = resultSet.getString("presc_days");
            prescriptions.add(new Prescription(prescID, prescRefPat, prescCode, prescDays));
        }

        return prescriptions;
    }

    public void update(String prescID, Prescription prescription) throws SQLException {
        String query = "UPDATE prescription SET presc_ref_pat = ?, presc_code = ?, presc_days = ? WHERE presc_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, prescription.getPrescRefPat());
        preparedStatement.setString(2, prescription.getPrescCode());
        preparedStatement.setString(3, prescription.getPrescDays());
        preparedStatement.setString(4, prescID);
        preparedStatement.executeUpdate();
    }

    public void delete(String prescID) throws SQLException {
        String query = "DELETE FROM prescription WHERE presc_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, prescID);
        preparedStatement.executeUpdate();
    }
}
