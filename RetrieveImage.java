package JDBC;

import java.io.*;
import java.lang.module.Configuration;
import java.sql.*;
import java.util.Stack;

public class RetrieveImage {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon",
                    "db_user_jon", "1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select f_name,f_type,f_data from image_attach");
            while (resultSet.next()) {
                String filName = resultSet.getString("f_name");
                String filType = resultSet.getString("f_type");


                InputStream inputStream = resultSet.getBinaryStream("f_data");
                byte [] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);

                File file = new File("img/" + filName + "_qq" + "." + filType);
                OutputStream outputStream = new FileOutputStream(file);
                outputStream.write(buffer);
                outputStream.close();
            }
            connection.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
