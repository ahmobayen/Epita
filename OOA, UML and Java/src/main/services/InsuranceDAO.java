package main.services;

import main.datamodel.Insurance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsuranceDAO {
    private Connection connection;

    public InsuranceDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Insurance insurance) throws SQLException {
        String query = "INSERT INTO insurance (insurance_id, insurance_name) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, insurance.getInsurance_id());
        preparedStatement.setString(2, insurance.getInsurance_name());
        preparedStatement.executeUpdate();
    }

    public Insurance read(Integer insurance_id) throws SQLException {
        String query = "SELECT * FROM insurance WHERE insurance_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, insurance_id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String insurance_name = resultSet.getString("insurance_name");
            return new Insurance(insurance_id, insurance_name);
        }

        return null;
    }


    public List<Insurance> readAll() throws SQLException {
        String query = "SELECT * FROM insurance";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Insurance> insurances = new ArrayList<>();

        while (resultSet.next()) {
            Integer insurance_id = resultSet.getInt("insurance_id");
            String insurance_name = resultSet.getString("insurance_name");
            insurances.add(new Insurance(insurance_id, insurance_name));
        }

        return insurances;
    }


    public void update(Integer insurance_id, Insurance insurance) throws SQLException {
        String query = "UPDATE insurance SET insurance_name = ? WHERE insurance_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, insurance.getInsurance_name());
        preparedStatement.setInt(2, insurance_id);
        preparedStatement.executeUpdate();
    }

    public void delete(Integer insurance_id) throws SQLException {
        String query = "DELETE FROM insurance WHERE insurance_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, insurance_id);
        preparedStatement.executeUpdate();
    }
}
