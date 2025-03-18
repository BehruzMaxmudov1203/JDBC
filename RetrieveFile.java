package JDBC;

import java.io.*;
import java.sql.*;
import java.util.Objects;

public class RetrieveFile {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon",
                    "db_user_jon", "1234");
            String sql = "select f_name,f_type,f_data from image_attach";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String filName = resultSet.getString("f_name");
                String filType = resultSet.getString("f_type");
                InputStream inputStream = resultSet.getBinaryStream("f_data");
                if (inputStream != null){
                    byte [] buffer = new byte[inputStream.available()];
                    inputStream.read(buffer);
                    File file = new File("img/" +filName + "_m" + "." + filType);
                    OutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(buffer);
                    outputStream.close();
                }
            }
            connection.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}