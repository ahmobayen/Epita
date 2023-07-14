package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestJDB1 {
    public static void test(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
            String query = "CREATE TABLE PATIENT (\n" +
                    "    pat_num_HC VARCHAR PRIMARY KEY ,\n" +
                    "    pat_lastname VARCHAR,\n" +
                    "    pat_firstname VARCHAR,\n" +
                    "    pat_address VARCHAR,\n" +
                    "    pat_tel VARCHAR,\n" +
                    "    pat_insurance_id VARCHAR,\n" +
                    "    pat_sub_date Date,\n" +
                    ");";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
