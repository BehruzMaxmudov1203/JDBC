package JDBC;

import jdk.jshell.execution.LoaderDelegate;

import java.sql.*;
import java.time.LocalDate;

public class PreparedStatementExample {
    public static void main(String[] args) {
        //insertExample("Behruz111", "Maxmudov", 22, "+998909793936", LocalDate.of(2003, 2, 22));
        //getListExample("h");
        // updateExample("+998808807896", 3);
        deleteExample(2);
    }

    public static Connection getConnection2() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon",
                    "db_user_jon", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void insertExample(String name, String surname, Integer age, String phone, LocalDate birthDate) {
        try {
            Connection connection = getConnection2();

            String sql = "insert into employee (name,surname,age,phone,birth_date) values (?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, phone);
            preparedStatement.setDate(5, Date.valueOf(birthDate));


            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getListExample(String qName) {
        Connection connection = getConnection2();
        String sql = "select * from employee where name like ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + qName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");
                LocalDate localDate = resultSet.getDate("birth_date").toLocalDate();

                System.out.println("id " + id + "name " + name + " surname " + surname + "age " + age +
                        " phone" + phone + " localDate " + localDate);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateExample(String phone, Integer empId) {

        try {
            Connection connection = getConnection2();
            String sql = "update employee set phone =? where id =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            preparedStatement.setInt(2, empId);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteExample(Integer empId) {
        try {
            Connection connection = getConnection2();
            String sql = "delete from employee where id=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,empId);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}