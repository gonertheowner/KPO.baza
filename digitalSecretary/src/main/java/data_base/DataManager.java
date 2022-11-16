package data_base;

import java.sql.*;


public class DataManager {
    public static final Connection connection;

    static
    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(ConfProperties.getProperty("db.URL"), ConfProperties.getProperty("db.login"), ConfProperties.getProperty("db.password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean selectAll(String tableName) throws SQLException {
        boolean flag;
        if (tableName == null) {
            flag = false;
            // OurClassException
        }

        var statement = connection.prepareStatement("SELECT * FROM ?");
        statement.setString(1, tableName);
        flag = statement.executeUpdate() != 0;
        return flag;
    }

    public static void addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Insert Into users (login, password) VALUES (?,?)");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // OurClassException
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(String tableName, int index) {
        boolean flag;
        if (tableName == null) {
            flag = false;
            // OurClassException
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ? WHERE ID=?");
            preparedStatement.setString(1, tableName);
            preparedStatement.setInt(2, index);
            flag = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public static boolean updateUser(int index, User user) {
        boolean flag;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET login=?, password=? WHERE ID=?");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, index);
            flag = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public static boolean searchUser(User user) {
        boolean flag = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT login FROM users WHERE EXISTS(SELECT login FROM users where login=?)");
            preparedStatement.setString(1, user.getLogin());
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            // OurClassException
            throw new RuntimeException(e);
        }
        return flag;
    }
}
