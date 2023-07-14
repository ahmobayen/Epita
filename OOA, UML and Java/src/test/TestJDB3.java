package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class TestJDB3 {
    public static void main(String[] args) throws FileNotFoundException, ParseException, SQLException {
        List<String> inputFiles = Arrays.asList("insurances", "medications", "patients", "prescriptions");
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
            for (String inputFileName : inputFiles) {
                String pathName = "resources/create-" + inputFileName + ".sql";
                String query = Files.readString(Path.of(pathName));

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
            }


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
