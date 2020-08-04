package by.realovka;

import java.sql.*;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost/pupils?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Vorobei55";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Pupil get() {
        Pupil pupil = new Pupil();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM class_1_a WHERE id=1");
            while (resultSet.next()) {
                pupil.setId(resultSet.getInt("id"));
                pupil.setName(resultSet.getString("name"));
                pupil.setSurname(resultSet.getString("surname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pupil;
    }

    public static void post() {
        try (Connection connection = DBConnector.getConnection()) {
            String sql = "INSERT INTO class_1_a (name, surname) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Игорь");
            preparedStatement.setString(2, "Карпов");
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete() {
        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM class_1_a WHERE surname='Григоренко'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void put() {
        try (Connection connection = DBConnector.getConnection()) {
            String sql = "UPDATE class_1_a SET surname=? WHERE name='Игорь'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Григоренко");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



