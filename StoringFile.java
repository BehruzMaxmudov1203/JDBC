package JDBC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoringFile {
    public static void main(String[] args) {
        try {
            File file = new File("dastur.txt");
            InputStream inputStream = new FileInputStream(file);

            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon",
                    "db_user_jon", "1234");
            String sql = "insert into image_attach (f_name,f_type,f_data) values (?,?,?)";
           PreparedStatement preparedStatement =  connection.prepareStatement(sql);
           preparedStatement.setString(1,"dastur");
           preparedStatement.setString(2,"txt");
           preparedStatement.setBinaryStream(3,inputStream);
           preparedStatement.executeUpdate();
           connection.close();
        } catch (FileNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}