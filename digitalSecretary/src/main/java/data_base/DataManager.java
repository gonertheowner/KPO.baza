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

        try
        {
            connection = DriverManager.getConnection(ConfProperties.getProperty("db.URL"), ConfProperties.getProperty("db.login"), ConfProperties.getProperty("db.password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void selectAll(String tableName) throws SQLException {
        if (tableName == null) {
            // trow some exception
        }
        var statement = connection.prepareStatement("SELECT * FROM " + tableName);
        if (statement.execute()) {
            System.out.println("Success!");
        } else {
            // throw some exception
        }
    }

    public static void addUser(User user) throws SQLException {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("Insert Into users (login, password) VALUES (?,?)");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteUser(int index) throws SQLException {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE ID=?");
            preparedStatement.setInt(1, index);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateUser (int index,User user) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE users SET login=?, password=? WHERE ID=?");
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setInt(3,index);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean searchUser(User user)
    {
        boolean flag=false;
       try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT login FROM users WHERE EXISTS(SELECT login FROM users where login=?)");
           preparedStatement.setString(1,user.getLogin());
           var resultSet=preparedStatement.executeQuery();
           if (resultSet.next()) {
               flag=true;
               System.out.println("есть такая строка");
           }
           else {
               System.out.println("нет такой строки");
           }

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
        return flag;
    }
}
